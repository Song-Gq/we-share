<template>
    <div>
        <card style="max-width: 1000px; margin: 20px auto 0 auto; padding: 0 20px">
            <Tabs v-model="tabType">
                <TabPane label="我的收藏" :disabled="postingTab" name="posting">
                    <List item-layout="vertical">
                        <ListItem v-for="item in favoriteList" :key="item.postingId" style="text-align: left">
                            <ListItemMeta :avatar="item.avatar" >
                                <template slot="description">
                                    <router-link v-for="topic in item.topic" :key="topic.id"
                                                 :to="{path: '/search', query:{ type: 'postingbytag', text: topic.id }}">
                                        <tag color="blue" >
                                            {{topic.name}}
                                        </tag>
                                    </router-link>
                                </template>
                                <template slot="title">
                                    <router-link :to="{path: '/posting', query:{ postingId: item.postingId }}">
                                        {{ item.title }}
                                    </router-link>
                                </template>
                            </ListItemMeta>
                            {{ item.content }}
                            <template slot="action">
                                <li>
                                    <Icon type="md-information-circle" /> 帖子ID {{item.postingId}}
                                </li>
                                <li>
                                    <Icon type="md-eye" /> 浏览量 {{item.views}}
                                </li>
                                <li>
                                    <Icon type="md-star" /> 收藏量 {{item.stars}}
                                </li>
                            </template>
                            <template slot="extra" style="max-height: fit-content">
                                <img :src="item.pic" style="max-height: 200px">
                            </template>
                        </ListItem>
                    </List>
                </TabPane>
                <TabPane label="我的帖子" :disabled="topicTab" name="topic">
                    <List>
                        <ListItem v-for="item in postList" :key="item.postingId" style="text-align: left">
                            <ListItemMeta avatar="" >
                                <template slot="description">
                                    <router-link v-for="topic in item.topic" :key="topic.id"
                                                 :to="{path: '/search', query:{ type: 'postingbytag', text: topic.id }}">
                                        <tag color="blue" >
                                            {{topic.name}}
                                        </tag>
                                    </router-link>
                                </template>
                                <template slot="title">
                                    <router-link :to="{path: '/posting', query:{ postingId: item.postingId }}">
                                        {{ item.title }}
                                    </router-link>
                                </template>
                            </ListItemMeta>
                            <template slot="action">
                                <li>
                                    <Icon type="ios-eye-outline" /> 浏览量 {{item.views}}
                                </li>
                                <li>
                                    <Icon type="ios-star-outline" /> 收藏量 {{item.stars}}
                                </li>
                            </template>
                        </ListItem>
                    </List>
                </TabPane>
                <TabPane label="我的关注" :disabled="userTab" name="user">
                    <List>
                        <ListItem v-for="item in userList" :key="item.userId" style="text-align: left">
                            <ListItemMeta   :description="item.intro">
                                <template slot="avatar">
                                    <avatar :src="item.avatar" size="40"/>
                                </template>
                                <template slot="title">
                                    <router-link :to="{path: '/personalPage', query:{ userId: item.userId }}">
                                        <div style="font-size:24px">
                                            {{ item.username }}
                                        </div>
                                    </router-link>
                                </template>
                            </ListItemMeta>
                            <template slot="action">
                                <li>
                                    <Button type="primary" @click="unfollow(item.userId)">取消关注</Button>
                                </li>
                            </template>
                        </ListItem>
                    </List>
                </TabPane>
            </Tabs>
        </card>
        <Button type="primary" :loading="loading" icon="ios-more" @click="toLoading" shape="circle" style="margin: 20px auto">
            <span v-if="!loading">加载更多</span>
            <span v-else>Loading...</span>
        </Button>
    </div>
</template>

<script>
    // import httpPop from "@/api/httpPop";
    import httpPersonPage from "@/api/httpPersonPage";
    // import httpSearch from "@/api/httpSearch";

    export default {
        name: "PersonalList",
        data () {
            return {
                loading: false,
                favoriteList: [],
                postList:[],
                userList:[],
                tabType: 'posting'
            }
        },
        computed: {
            postingTab: function () {
                if(this.$route.query.type === "posting"
                    || this.$route.query.type === undefined
                    || this.$route.query.type === "postingbytag" ) {
                    return false;
                }
                return true;
            },
            topicTab: function () {
                if(this.$route.query.type === "topic" || this.$route.query.type === undefined) {
                    return false;
                }
                return true;
            },
            userTab: function () {
                if(this.$route.query.type === "user"|| this.$route.query.type === undefined) {
                    return false;
                }
                return true;
            }
        },
        methods: {
            handleReachBottom () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        const last = this.favoriteList.length;
                        var idx;
                        for (let i = 1; i < 6; i++) {
                            idx = last + i;
                            if (this.tabType === 'posting') {
                                this.favoriteList.push({
                                    postingId: 'posting id ' + idx,
                                    title: "This is title " + idx,
                                    topic: [ 'Topic1', 'Topic2', 'Topic3' ],
                                    avatar: require('../assets/avatar/' + idx + '.jpg'),
                                    content: 'This is the content, this is the content, this is the content, this is the content.',
                                    pic: require('../assets/pic/' + idx + '.jpg'),
                                });
                            }
                            else if (this.tabType === 'topic') {
                                this.postList.push({
                                    postingId: 'posting id ' + idx,
                                    title: "This is title " + idx,
                                    topic: [ 'Topic1', 'Topic2', 'Topic3' ],
                                    avatar: require('../assets/avatar/' + idx + '.jpg'),
                                    content: 'This is the content, this is the content, this is the content, this is the content.',
                                    pic: require('../assets/pic/' + idx + '.jpg'),
                                });
                            }
                            else if (this.tabType === 'user') {
                                this.userList.push({
                                    userName: 'newusername' + idx,
                                    userId: 'newuserid' + idx,
                                    avatar: require('../assets/avatar/' + idx + '.jpg')
                                });
                            }
                        }
                        resolve();
                        this.loading = false
                    }, 2000);
                });
            },
            pageResize(){
                this.$nextTick(()=>{
                    this.avaiHeight = document.documentElement.clientHeight - 60
                })
            },
            unfollow(name){
                httpPersonPage.unfollowUser(this.$root.userId,name,data=>{
                    if(data['isSuccess']===0){
                        this.$Message.success('取关成功！')
                        this.updateList()
                    }
                    else
                        this.$Message.error("取关失败")
                })
            },
            toLoading(){
                this.loading = true
                this.handleReachBottom()
            },
            changeType(){
                if(this.tabType==='posting')
                    return 'myFavorite'
                else if(this.tabType==='topic')
                    return "myPost"
                else if(this.tabType==='user')
                    return 'myFocus'
            },
            updateList() {
                this.favoriteList = undefined
                this.postList = undefined
                this.userList = undefined

                // window.console.log(this.tabType)
                var type=this.changeType()
                // window.console.log(type)

                httpPersonPage.getMyPage(this.$root.userId,type,'1', data=>{
                    if(type==='myFavorite')
                        this.favoriteList=data
                    else if(type==='myPost')
                        this.postList=data
                    else if(type==='myFocus')
                        this.userList=data
                })
            }
        },
        created(){
            this.updateList()
        },
        mounted(){
            let _this = this;
            window.onresize = ()=>{
                _this.pageResize();
            }
        },
        destroyed(){
            window.onresize = null;
        },
        watch: {
            'tabType'() {
                // 对tab变化作出响应...
                this.updateList()
            }
        },
    }




</script>

<style scoped>

</style>