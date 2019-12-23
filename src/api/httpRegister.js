import axios from 'axios'

const baseURL=''

const http=axios.create(
    {
        baseURL:baseURL,
        headers:{
            'Content-Type': 'application/json'
        }
    }
)

export default {
    get(email,name,pwd,callback){
        http.request({
            url:baseURL=='testData'?'/register.json':'/register',
            method:'GET',
            params:{
                email:email,
                userName:name,
                password:pwd
            }
            }).then(function (response) {
                const result=response.data;
                callback(result)
        }).catch(function (error) {
            window.console.log(error)
        })
    }
}