import axios from 'axios';

// 配置了 url 则使用url；
// 如未配置，则使用测试的数据。
const baseURL = '' || 'testData';

const http = axios.create({
    baseURL: baseURL,
    headers: {
        'Content-Type': 'application/json'
    },
});

const dataHandler = data => {
    // 可以在这里添加一些数据处理。
    return data;
};

export default {
    get(searchType, page, callback) {
        http.request({
            url: baseURL == 'testData' ? (
                searchType == 'posting' ? '/popPosting.json' : '/popTopic.json'
            ) : '/index',
            method: 'GET',
            params: {
                type: searchType,
                page: page
            }
        }).then(response => {
            // window.console.log(response.data)
            const result = response.data.list.map(item => dataHandler(item));
            // 因为 axios 的请求是异步请求，所以在这里使用了回调函数。
            callback(result);
        }).catch(function (error) {
            window.console.log(error);
        });
    }
};
