import axios from 'axios';

// 配置了 url 则使用url；
// 如未配置，则使用测试的数据。
const baseURL = '';

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
    get(questionId, answerPage, callback) {
        http.request({
            url: baseURL == 'testData' ? 'postingAnswer.json' : '/posting/answer',
            method: 'GET',
            params: {
                questionId: questionId,
                answerPage: answerPage
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
