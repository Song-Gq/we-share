<template>
    <div class="register">
        <div class="from-wrap">
            <h2>注册</h2>
            <Form ref="registerData" :model="registerData" :rules="ruleValidate" :label-width="80">
                <FormItem label="Email" prop="account">
                    <label>
                        <Input type="email" v-model="registerData.account"  placeholder="请输入账号"/>
                    </label>
                </FormItem>
                <FormItem label="Name" prop="name">
                    <label>
                        <Input type="text" v-model="registerData.name" placeholder="请输入昵称"/>
                    </label>
                </FormItem>
                <FormItem label="Password" prop="password">
                    <label>
                        <Input type="password" v-model="registerData.password" placeholder="请输入密码"
                               @keyup.enter.native="handleSubmit('registerData')"/>
                    </label>
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
    import httpRegister from "@/api/httpRegister";

    export default {
        name: "Register",
        data(){
            return {
                registerData: {
                    account:'',
                    name:'',
                    password:''
                },
                ruleValidate: {
                    account: [
                        { required: true, message: '账号不能为空', trigger: 'blur' },
                        { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
                    ],
                    name:[
                        {required:true,message:'昵称不可为空',trigger:'blur'}
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
                        var encodePwd;
                        encodePwd = window.btoa(this.registerData.password)
                        httpRegister.get(this.registerData.account,this.registerData.name,encodePwd,data=>{
                            // window.console.log(this.registerData.account)
                            if(data['isSuccess']===0){
                                this.$Message.success('注册成功!')
                                this.$root.hasLogin=true
                                this.$root.userId=data['userId']
                                this.$router.push({path:"index"})
                            }
                            else{
                                this.$Message.error('该邮箱已被注册！')
                            }
                        })
                    } else {
                        this.$Message.error('输入信息格式错误!')
                    }
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields()
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
        height: 340px;
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