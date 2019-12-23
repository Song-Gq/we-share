<template>
    <div style="display: flex; justify-content: space-between">
        <div dir="rtl">
            <Scroll :height="avaiHeight" style="max-width: 1200px; margin: 0 20px 0 0; min-width: 1000px">
                <div dir="ltr">
                    <card style="margin-top: 20px; margin-left: 50px; padding: 0 20px">
                        <List item-layout="vertical">
                            <ListItem :key="postingId" style="text-align: left; display: flex">
                                <ListItemMeta :title="posting.title" :avatar="posting.avatar" >
                                    <template slot="description" v-for="topic in posting.topic">
                                        {{topic}}
                                    </template>
                                </ListItemMeta>
                                <template slot="action">
                                    <li @click="follow">
                                        <Icon type="md-add" /> 关注用户
                                    </li>
                                    <li @click="star">
                                        <Icon type="md-star" /> 收藏帖子
                                    </li>
                                    <li @click="toPersonal">
                                        <Icon type="md-eye" /> 查看用户
                                    </li>
                                </template>
                            </ListItem>
                            <ListItem v-for="item in list" :key="item.time" style="text-align: left">
                                <ListItemMeta :description="item.time" />
                                {{ item.content }}
                                <template slot="extra" style="max-height: fit-content">
                                    <img :src="item.pic" style="max-height: 200px">
                                </template>
                            </ListItem>
                        </List>
                    </card>
                    <Button type="primary" :loading="loading" icon="ios-more" @click="toLoading"
                            shape="circle" style="margin: 20px auto">
                        <span v-if="!loading">加载更多</span>
                        <span v-else>Loading...</span>
                    </Button>
                    <Button type="primary" icon="md-hammer" @click="append"
                            shape="circle" style="margin: 20px" :disabled="!ableToAppend">
                        <span v-if="!ableToAppend">只有贴主才可以盖楼</span>
                        <span v-else>盖楼</span>
                    </Button>
                </div>
            </Scroll>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    import httpPostingList from "@/api/httpPostingList";
    import httpFollow from "@/api/httpFollow";
    import httpStar from "@/api/httpStar";

    export default {
        name: "Posting",
        data () {
            return {
                avaiHeight: document.documentElement.clientHeight - 60,
                loading: false,
                posting: [],
                list: [],
                postingPage: 0,
                // hasLogin: this.$hasLogin
                // userId: this.$userId
            }
        },
        computed: {
            postingId: function () {
                return this.$route.query.postingId
            },
            ableToAppend: function () {
                if(this.$root.hasLogin && this.$root.userId === this.posting.posterId)
                    return true
                return false
            }
        },
        methods: {
            handleReachBottom () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        httpPostingList.get(this.postingId, this.postingPage + 1,
                            0 ,data=>{
                                for (let idx in data.list)
                                    this.list.push(data.list[idx])
                                this.postingPage += 1
                        })
                        // const last = this.list.length;
                        // var idx;
                        // for (let i = 1; i < 6; i++) {
                        //     idx = last + i;
                        //     this.list.push({
                        //         contentIdx: idx,
                        //         time: "This is time " + idx,
                        //         content: 'This is the content, this is the content, this is the content, this is the content.',
                        //         pic: require('../assets/pic/' + idx + '.jpg'),
                        //     });
                        // }
                        resolve();
                        this.loading = false
                    }, 10);
                });
            },
            pageResize(){
                this.$nextTick(()=>{
                    this.avaiHeight = document.documentElement.clientHeight - 60
                })
            },
            toLoading(){
                this.loading = true
                this.handleReachBottom()
            },
            append() {
                this.$router.push({path: '/edit', query: { postingId: this.postingId }})
            },
            follow() {
                if(this.$root.hasLogin !== true) {
                    this.$Message.warning("需要登录")
                }
                else if (this.$root.userId === this.posting.posterId) {
                    this.$Message.warning("不能关注自己")
                }
                else {
                    httpFollow.get(this.posting.posterId, this.$root.userId, data=>{
                        if(data === 200)
                            this.$Message.success("关注成功")
                        else
                            this.$Message.warning("已在关注列表")
                    })
                }
            },
            star() {
                if(this.$root.hasLogin !== true) {
                    this.$Message.warning("需要登录")
                }
                else if (this.$root.userId === this.posting.posterId) {
                    this.$Message.warning("不能关注自己的帖子")
                }
                else {
                    httpStar.get(this.postingId, this.$root.userId, data=>{
                        if(data === 200)
                            this.$Message.success("收藏成功")
                        else
                            this.$Message.warning("已在收藏列表")
                    })
                }
            },
            toPersonal() {
                this.$router.push({path: '/personalpage', query:{ userId: this.posting.posterId }})
            }
        },
        mounted(){
            let _this = this;
            if(window.onresize !== null) {
                var oldOnresize = window.onresize;
                window.onresize = ()=> {
                    oldOnresize();
                    _this.pageResize()
                }
            }
            else {
                window.onresize = ()=>{
                    _this.pageResize();
                }
            }
        },
        destroyed(){
            window.onresize = null;
        },
        created() {
            httpPostingList.get(this.postingId, 1, 0, data=>{
                this.list = data.list
                this.posting = data.posting
                this.postingPage = 1
            })
        }
    }

</script>

<style scoped>

</style>