import axios from 'axios'

const baseURL=''||'testData';

const http=axios.create({
    baseURL:baseURL,
    headers:{
        'Content-Type': 'application/json'
    }
})

// const dataHandler = data => {
//     // 可以在这里添加一些数据处理。
//     return data;
// };

export default {
    get(email,pwd,callback){
        http.request({
            url: '/loginUser.json',
            method: 'GET',
            params: {
                email: email,
                password: pwd
            }
        }).then(function (response) {
            const result=response.data.list;
            callback(result)
        }).catch(function (error) {
            window.console.log(error);
        })
    }
}