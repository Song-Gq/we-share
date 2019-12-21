<template>
    <div style="width: 800px;margin: 20px auto 0 auto;padding:20px 20px;background: snow">
        <div style="display: flex">
            <Button type="dashed" to="/personalPage">
                <Icon type="ios-arrow-back" />
                Backward
            </Button>
        </div>

        <div style="width: 500px;margin: 50px auto 0 auto">
            <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80">
                <FormItem label="" prop="avatar" >
                    <avatar :src="formValidate.avatar" size="80" style="float: left"/>
                    <div>
                        <Button icon="ios-camera" style="margin: 25px 140px 0 0" @click.stop="uploadHeadImg">更换头像</Button>
                        <input type="file" accept="image/*" @change="handleFile" class="hiddenInput" style="display: none"/>
                    </div>
                </FormItem>
                <FormItem label="昵称" prop="name">
                    <Input v-model="formValidate.name" placeholder="Enter your name"/>
                </FormItem>
                <FormItem label="邮箱" prop="mail">
                    <Input v-model="formValidate.mail" placeholder="Enter your e-mail"/>
                </FormItem>
                <FormItem label="性别" prop="gender">
                    <RadioGroup v-model="formValidate.gender" style="display: flex">
                        <Radio label="男">男</Radio>
                        <Radio label="女">女</Radio>
                    </RadioGroup>
                </FormItem>
<!--                <FormItem label="城市" prop="city">-->
<!--                    <Select v-model="formValidate.city" placeholder="Select your city">-->
<!--                        <Option value="beijing">New York</Option>-->
<!--                        <Option value="shanghai">London</Option>-->
<!--                        <Option value="shenzhen">Sydney</Option>-->
<!--                    </Select>-->
<!--                </FormItem>-->
<!--                <FormItem label="生日">-->
<!--                    <Row>-->
<!--                        <i-col span="11">-->
<!--                            <FormItem prop="date">-->
<!--                                <DatePicker type="date" placeholder="Select date" v-model="formValidate.date"/>-->
<!--                            </FormItem>-->
<!--                        </i-col>-->
<!--                    </Row>-->
<!--                </FormItem>                -->
<!--                <FormItem label="Hobby" prop="interest">-->
<!--                    <CheckboxGroup v-model="formValidate.interest" style="display:flex">-->
<!--                        <Checkbox label="Eat"/>-->
<!--                        <Checkbox label="Sleep"/>-->
<!--                        <Checkbox label="Run"/>-->
<!--                        <Checkbox label="Movie"/>-->
<!--                    </CheckboxGroup>-->
<!--                </FormItem>-->
                <FormItem label="简介" prop="desc">
                    <Input v-model="formValidate.desc" type="textarea" :autosize="{minRows: 2,maxRows: 5}"
                           placeholder="Enter something to introduce yourself"/>
                </FormItem>
                <FormItem>
                    <Button type="primary" @click="handleSubmit('formValidate')">Submit</Button>
                    <Button @click="handleReset('formValidate')" style="margin-left: 8px">Reset</Button>
                </FormItem>
            </Form>
        </div>
    </div>


</template>
<script>
    import htmlPersonPage from "@/api/httpPersonPage";

    export default {
        name:"ChangeInfo",
        data () {
            return {
                formValidate: {
                    avatar:'',
                    name: '',
                    mail: '',
                    gender:'',
                    // city: '',
                    // interest: [],
                    // date: '',
                    // time: '',
                    desc: ''
                },
                ruleValidate: {
                    name: [
                        { required: true, message: 'The name cannot be empty', trigger: 'blur' }
                    ],
                    mail: [
                        { required: true, message: 'Mailbox cannot be empty', trigger: 'blur' },
                        { type: 'email', message: 'Incorrect email format', trigger: 'blur' }
                    ],
                    city: [
                        { required: true, message: 'Please select the city', trigger: 'change' }
                    ],
                    gender: [
                        { required: true, message: 'Please select gender', trigger: 'change' }
                    ],
                    interest: [
                        { required: true, type: 'array', min: 1, message: 'Choose at least one hobby', trigger: 'change' },
                        { type: 'array', max: 2, message: 'Choose two hobbies at best', trigger: 'change' }
                    ],
                    date: [
                        { required: true, type: 'date', message: 'Please select the date', trigger: 'change' }
                    ],
                    time: [
                        { required: true, type: 'string', message: 'Please select time', trigger: 'change' }
                    ],
                    desc: [
                        { required: true, message: 'Please enter a personal introduction', trigger: 'blur' },
                        { type: 'string', min: 10, message: 'Introduce no less than 20 words', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            showForm(id){
                htmlPersonPage.getUserInfo(id,data=>{
                    this.formValidate.avatar=data['avatar']
                    this.formValidate.name=data['userName']
                    this.formValidate.mail=data['email']
                    this.formValidate.desc=data['introduction']
                    if(data['gender']===0)
                        this.formValidate.gender='男'
                    else
                        this.formValidate.gender='女'
                })
            },
            handleSubmit (name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        if (this.formValidate.gender === '男')
                            var _gender = 0
                        else
                            _gender = 1
                        // window.console.log(this.formValidate.avatar,this.formValidate.name,_gender,
                        //     this.formValidate.mail,this.formValidate.desc)
                        htmlPersonPage.changeUserInfo(this.formValidate.avatar, this.formValidate.name, _gender,
                            this.formValidate.mail, this.formValidate.desc, data => {
                                if (data['isSuccess'] === 0)
                                    this.$Message.success('修改成功!');
                                else
                                    this.$Message.error("修改失败，请重新提交！")
                            })

                    } else {
                        this.$Message.error('信息格式不正确!');
                    }
                })
            },
            handleReset (name) {
                this.$refs[name].resetFields();
            },

            uploadHeadImg: function () {
                this.$el.querySelector('.hiddenInput').click()
            },
            // 将头像显示
            handleFile: function (e) {
                let $target = e.target || e.srcElement
                let file = $target.files[0]
                var reader = new FileReader()
                reader.onload = (data) => {
                    let res = data.target || data.srcElement
                    this.formValidate.avatar = res.result
                }
                reader.readAsDataURL(file)
            },
        },
        created(){
            // window.console.log(this.$root.hasLogin)
            if(this.$root.hasLogin===true){
                this.showForm(this.$root.userId)
            }
        }
    }
</script>
