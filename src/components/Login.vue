<template>
    <div class="login">
        <div class="from-wrap">
            <h2>登录</h2>
            <Form ref="loginData" :model="loginData" :rules="ruleValidate" :label-width="80">
                <FormItem label="Email" prop="account">
                    <Input type="email" v-model="loginData.account" placeholder="请输入账号"/>
                </FormItem>
                <FormItem label="Password" prop="password">
                    <Input type="password" v-model="loginData.password" placeholder="请输入密码"/>
                </FormItem>
                <FormItem class="form-footer">
                    <Button type="primary" @click="handleSubmit('loginData')">Submit</Button>
                    <Button  @click="handleReset('loginData')" style="margin-left: 8px">Reset</Button>
                </FormItem>
                <router-link to="register">没有账户？注册一个</router-link>
            </Form>
        </div>
    </div>
</template>

<script>
    import httpLogin from "@/api/httpLogin";

    export default {
        name:"Login",
        data () {
            return {
                loginData: {
                    account:'',
                    password:''
                },
                ruleValidate: {
                    account: [
                        { required: true, message: '账号不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, max: 16, message: '密码长度6-16个字符', trigger: 'blur' }
                    ]
                },
            }
        },
        methods: {
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        httpLogin.get(this.loginData.account,this.loginData.password,data=>{
                            if(data['isSuccess']===0) {
                                // window.console.log(data)
                                // window.console.log(this.$root.hasLogin)
                                this.$Message.success('登录成功!')
                                this.$root.hasLogin=true
                                this.$root.userId=data['userId']
                                // window.console.log(this.$root.hasLogin)
                                this.$router.push({path:"index"})
                            }
                            else {
                                this.$Message.error('登录失败，账户或密码错误!')
                            }
                        })
                    } else {
                        this.$Message.error('输入信息格式错误!')
                    }
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            }
        }
    }
</script>

<style>
    .login {
        width: 100%;
        height: 100%;
        /*background-color: #1c2438;*/
        position: relative;
    }
    .login .from-wrap{
        position: fixed;
        left: 50%;
        margin-left: -200px;
        top: 50%;
        margin-top: -150px;
        width: 400px;
        height: 280px;
        border-radius: 10px;
        background-color: powderblue;
        padding: 20px 30px;
    }
    .login h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .login FormItem {
        margin-bottom: 15px;
    }
    .login .form-footer {
        text-align: right;
    }
    .ivu-form-item-required .ivu-form-item-label:before {
        display: none;
    }
</style>