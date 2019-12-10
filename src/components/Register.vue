<template>
    <div class="register">
        <div class="from-wrap">
            <h2>注册</h2>
            <Form ref="registerData" :model="registerData" :rules="ruleValidate" :label-width="80">
                <FormItem label="Account" prop="account">
                    <Input type="text" v-model="registerData.account" placeholder="请输入账号"/>
                </FormItem>
                <FormItem label="Password" prop="password">
                    <Input type="password" v-model="registerData.password" placeholder="请输入密码"/>
                </FormItem>
                <FormItem class="form-footer">
                    <Button type="primary" @click="handleSubmit('registerData')">Submit</Button>
                    <Button  @click="handleReset('registerData')" style="margin-left: 8px">Reset</Button>
                </FormItem>
            </Form>
            <router-link to="login">已有账户？去登录</router-link>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data(){
            return {
                registerData: {
                    account:'',
                    password:''
                },
                ruleValidate: {
                    account: [
                        { required: true, message: '账号不能为空', trigger: 'blur' },
                        { min: 3, max: 16, message: '账号长度3-16个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '密码不能为空', trigger: 'blur' },
                        { type: 'string', min: 6, max: 16, message: '密码长度6-16个字符', trigger: 'blur' }
                    ]
                }
            }
        },
        methods:{
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$Message.success('注册成功!');
                        /**/
                        this.$router.push({path:"index"})
                    } else {
                        this.$Message.error('注册失败!')
                    }
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            }
        }
    }
</script>

<style >
    .register {
        width: 100%;
        height: 100%;
        position: relative;
    }
    .register .from-wrap{
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
    .register h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .register FormItem {
        margin-bottom: 15px;
    }
    .register .form-footer {
        text-align: right;
    }
    .ivu-form-item-required .ivu-form-item-label:before {
        display: none;
    }
</style>