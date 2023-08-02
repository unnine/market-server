(function fetchModule(ajaxModule) {
    window.FETCH = ajaxModule;
}(
    (function ajaxModule() {

        const toQueryString = (param) => {
            if (!param) {
                return '';
            }

            const queryString = Object.entries(param)
                .filter(([key, value]) => value != null)
                .map(([key, value]) => `${key}=${value}`)
                .join('&');

            return window.encodeURI('?' + queryString);
        };

        const request = async (method, url, param) => {
            const option = {
                method,
                mode: 'cors',
                cache: 'no-cache',
                credentials: 'same-origin',
                headers: {
                    'Content-Type': 'application/json'
                },
            };

            if (method === 'GET' || method === 'DELETE') {
                option.body = JSON.stringify(param);
            }

            return await window.fetch(url, option);
        }

        const requestWithoutBody = (method, url, param) => {
            return request(method, url + toQueryString(param));
        }

        const requestWithBody = (method, url, param) => {
            return request(method, url, param);
        }

        return {
            get: (url, param) => requestWithoutBody('GET', url, param),
            delete: (url, param) => requestWithoutBody('DELETE', url, param),
            post: (url, param) => requestWithBody('POST', url, param),
            put: (url, param) => requestWithBody('PUT', url, param),
        };
    }())
));