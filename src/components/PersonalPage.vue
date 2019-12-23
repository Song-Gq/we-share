<template>
    <div>
        <div  id="upper" v-bind="user">
           <div style="margin-left: 20px">
                <avatar :src="user.avatar" size="150"  />
            </div>
            <div id="right">
                <div style="margin-top: 40px">
                    <h1>{{user.username}}</h1>
                </div>
                <Tooltip max-width="300" :content="user.intro">
                    <div class="text">
                        <Icon type="md-information-circle" size="24"/>&nbsp;{{user.intro}}
                    </div>
                </Tooltip>
            </div>
            <div style="margin-top:110px;margin-left: 240px">
                <Button type="primary"  ghost to="/changeInfo" :disabled="editable">编辑个人资料</Button>
            </div>
        </div>
        <div>
            <personal-list></personal-list>
        </div>
    </div>

</template>

<script>
    import PersonalList from "@/components/PersonalList";
    import httpPersonPage from "@/api/httpPersonPage";
    export default {
        name: "PersonalPage",
        components: {PersonalList},
        data(){
            return {
                user: {
                        avatar:'',
                        username:'',
                        intro:''
                }
            }
        },
        methods:{
           showInfo(){
               if(this.$root.hasLogin===true)
                   httpPersonPage.getUserInfo(this.$route.query.userId,data=>{
                       this.user.avatar=data['avatar']
                       this.user.username=data['userName']
                       this.user.intro=data['introduction']
                   })
           }
        },
        created(){
            this.showInfo()
        },
        computed: {
            editable: function () {
                if(this.$root.userId === this.$route.query.userId)
                    return false
                return true
            }
        }
    }
</script>

<style>
    #upper{
        display:-webkit-flex;
        display: inline-flex;
        background: whitesmoke;
        justify-content:flex-start;
        -webkit-justify-content: flex-start;
        align-items: center;
        width: 1000px;
        height: 200px;
        margin: 20px auto 0 auto;
        padding: 0 20px;
    }
    #right{
        display: flex;
        display: -webkit-flex;
        justify-content: flex-start;
        -webkit-justify-content: flex-start;
        align-items: flex-start;
        flex-direction: column;
        margin-left: 20px;
        height: 200px;
    }
    .text{
        position: relative;
        margin-top: 30px;
        height: 70px;
        text-align: left;
        word-wrap: break-word;
        word-break: break-all;
        overflow: hidden; /*自动隐藏文字*/
        text-overflow: ellipsis;/*文字隐藏后添加省略号*/
        /*white-space: nowrap;/*强制不换行*/
        width: 20em;/*不允许出现半汉字截断*/
        color:#6699ff;
        /*border:1px #ff8000 dashed;*/
        display: -webkit-box;
        -webkit-line-clamp:3; /*想要显示的行数*/
        -webkit-box-orient: vertical;
    }
</style>