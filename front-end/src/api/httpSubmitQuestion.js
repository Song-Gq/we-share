import axios from 'axios';

const baseURL = '';

const http = axios.create({
    baseURL: baseURL,
    headers: {
        'Content-Type': 'application/json'
    },
});

// const dataHandler = data => {
//     // 可以在这里添加一些数据处理。
//     return data;
// };

export default {
    get(postingId, content, questionerId, callback) {
        http.request({
            url: baseURL == 'testData' ? 'editSubmit.json' : '/submitquestion',
            method: 'GET',
            params: {
                postingId: postingId,
                content: content,
                questionerId: questionerId
            }
        }).then(response => {
            // window.console.log(response.data)
            const result = response.data.response;
            // 因为 axios 的请求是异步请求，所以在这里使用了回调函数。
            callback(result);
        }).catch(function (error) {
            window.console.log(error);
        });
    }
};
