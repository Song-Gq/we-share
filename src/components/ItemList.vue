<template>
    <Scroll :height="avaiHeight" style="position: relative; z-index: 0">
        <card style="max-width: 1200px; margin: 20px auto 0 auto; padding: 0 20px">
            <Tabs v-model="tabType" :animated="false">
                <TabPane :label="popularPosting" :disabled="postingTab" name="posting">
                    <List item-layout="vertical">
                        <ListItem v-for="item in list" :key="item.postingId" style="text-align: left">
                            <ListItemMeta :avatar="item.avatar" >
                                <template slot="description">
                                    <router-link v-for="topic in item.topic" :key="topic.tagId"
                                                 :to="{path: '/search', query:{ type: 'postingbytag', text: topic.tagId }}">
                                        <tag color="blue" >
                                            {{topic.tagName}}
                                        </tag>
                                    </router-link>
                                </template>
                                <template slot="title">
                                    <router-link :to="{path: 'posting', query:{ postingId: item.postingId }}">
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
                <TabPane :label="popularTopic" :disabled="topicTab" name="topic" >
                    <div style="text-align: left">
<!--                        <Card icon="md-pricetag" :padding="0" style="width: 300px; margin: 0 auto">-->
                            <CellGroup v-for="item in tlist" :key="item.tagId" style="padding: 5px">
                                <Cell :title="item.tagName" extra="搜索该话题下的帖子"
                                      :to="{path: '/search', query:{ type: 'postingbytag', text: item.tagId }}" >
                                    <template slot="label">
<!--                                        话题: {{item.tagId}}-->
                                    </template>
                                </Cell>
                            </CellGroup>
<!--                        </Card>-->
                    </div>
                </TabPane>
                <TabPane :label="popularUser" :disabled="userTab" name="user">
                    <div style="text-align: left">
                        <CellGroup v-for="item in ulist" :key="item.userId" style="padding: 5px">
                            <Cell :to="{path: '/personalpage', query:{ userid: item.userId }}" >
                                <avatar :src="item.avatar" />
                                <template>
                                    {{item.userName}}
                                </template>
                                <template slot="label">
                                    用户ID: {{item.userId}}
                                </template>
                            </Cell>
                        </CellGroup>
                    </div>
                </TabPane>
            </Tabs>
        </card>
        <Button type="primary" :loading="loading" icon="ios-more" @click="toLoading"
                shape="circle" style="margin: 20px auto">
            <span v-if="!loading">加载更多</span>
            <span v-else>Loading...</span>
        </Button>
    </Scroll>
</template>

<script>
    import httpPopList from "@/api/httpPopList";
    import httpSearch from "@/api/httpSearch";

    export default {
        name: "ItemList",
        data () {
            return {
                avaiHeight: document.documentElement.clientHeight - 60,
                loading: false,
                tabTypeData: "",
                list: [],
                tlist: [],
                ulist: [],
                curPage: 0
            }
        },
        computed: {
/*            list: function () {
                if (this.$route.path.substr(1).split('/')[0] === 'index') {
                    httpPop.get('posting', '1', data => {
                        //window.console.log(data)
                        return data
                    })
                }
                if(this.$route.query.type === "posting"
                    || this.$route.query.type === undefined
                    || this.$route.query.type === "postingbytag" )
                {
                    window.console.log(this.$route.query.list)
                    return this.$route.query.list
                }
                window.console.log("!!!!!!!")
                return undefined
            },
            tlist: function() {
                if (this.$route.path.substr(1).split('/')[0] === 'index')
                    httpPop.get('topic', '1', data=>{
                        //window.console.log(data)
                        return data
                    })
                if(this.$route.query.type === "topic" || this.$route.query.type === undefined)
                    return this.$route.query.list
                return undefined
            },
            ulist: function() {
                if(this.$route.query.type === "user")
                    return this.$route.query.list
                return undefined
            },*/
            tabType: {
                get() {
                    this.pageResize()
                    if(this.$route.query.type === "postingbytag" || this.tabTypeData === 'postingbytag')
                        return "posting"
                    if(this.$route.query.type !== undefined)
                        return this.$route.query.type
                    else if (this.tabTypeData !== '' && this.tabTypeData !== 'user')
                        return this.tabTypeData
                    return "posting"
                },
                set(val) {
                    this.tabTypeData = val
                }
            },
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
                if(this.$route.query.type === "user") {
                    return false;
                }
                return true;
            },
            popularPosting: function () {
                if(this.$route.query.type === undefined)
                    return "热门帖子"
                else if (this.$route.query.type === "postingbytag")
                    return "话题下帖子的搜索结果"
                return "帖子的搜索结果"
            },
            popularTopic: function () {
                if(this.$route.query.type === undefined)
                    return "热门话题"
                return "话题的搜索结果"
            },
            popularUser: function () {
                if(this.$route.query.type === undefined)
                    return "热门用户"
                return "用户的搜索结果"
            }
/*            avaiHeight: function () {
                return document.documentElement.clientHeight - 60
            }*/
        },
        methods: {
            handleReachBottom () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        if (this.$route.query.type === undefined) {
                            httpPopList.get(this.tabType, this.curPage + 1, data=>{
                                if(this.tabType === 'posting') {
                                    for (let idx in data)
                                        this.list.push(data[idx])
                                }
                                else {
                                    for (let idx in data)
                                        this.tlist.push(data[idx])
                                }
                                this.curPage += 1
                            })
                        }
                        else {
                            httpSearch.get(this.$route.query.type, this.$route.query.text,
                                this.curPage + 1, data=>{
                                    if(this.tabType === 'topic') {
                                        for (let idx in data)
                                            this.tlist.push(data[idx])
                                    }
                                    else if(this.tabType === 'user') {
                                        for (let idx in data)
                                            this.ulist.push(data[idx])
                                    }
                                    else {
                                        for (let idx in data)
                                            this.list.push(data[idx])
                                    }
                                    this.curPage += 1
                            })
                        }
                        // const last = this.list.length;
                        // var idx;
                        // for (let i = 1; i < 6; i++) {
                        //     idx = last + i;
                        //     if (this.tabType === 'posting') {
                        //         this.list.push({
                        //             postingId: 'posting id ' + idx,
                        //             title: "This is title " + idx,
                        //             topic: [ 'Topic1', 'Topic2', 'Topic3' ],
                        //             avatar: require('../assets/avatar/' + idx + '.jpg'),
                        //             content: 'This is the content, this is the content, this is the content, this is the content.',
                        //             pic: require('../assets/pic/' + idx + '.jpg'),
                        //         });
                        //     }
                        //     else if (this.tabType === 'topic') {
                        //         this.tlist.push({
                        //             tagName: 'newtagname' + idx,
                        //             tagId: 'newtagid' + idx
                        //         });
                        //     }
                        //     else if (this.tabType === 'user') {
                        //         this.ulist.push({
                        //             userName: 'newusername' + idx,
                        //             userId: 'newuserid' + idx,
                        //             avatar: require('../assets/avatar/' + idx + '.jpg')
                        //         });
                        //     }
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
            updateList() {
                this.list = undefined
                this.tlist = undefined
                this.ulist = undefined

                if (this.$route.path.substr(1).split('/')[0] === 'index') {
                    httpPopList.get('posting', '1', data => {
                        //window.console.log(data)
                        this.list = data
                    })
                    httpPopList.get('topic', '1', data => {
                        //window.console.log(data)
                        this.tlist = data
                    })
                }
                else {
                    httpSearch.get(this.$route.query.type, this.$route.query.text, 1, data=>{
                        if(this.$route.query.type === "posting"
                            || this.$route.query.type === undefined
                            || this.$route.query.type === "postingbytag" )
                            this.list = data
                        else if(this.$route.query.type === "topic" || this.$route.query.type === undefined)
                            this.tlist = data
                        else if(this.$route.query.type === "user")
                            this.ulist = data
                    })
                }
                this.curPage = 1
            }
        },
        created(){
            this.updateList()
        },
        mounted() {
            let _this = this;
            window.onresize = ()=>{
                _this.pageResize();
            }
        },
        destroyed(){
            window.onresize = null;
        },
        watch: {
            '$route'() {
                // 对路由变化作出响应...
                this.updateList()
            }
        },
        // beforeRouteUpdate (to, from, next) {
        //     window.console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!")
        //     this.list = undefined
        //     this.tlist = undefined
        //     this.ulist = undefined
        //
        //     if (this.$route.path.substr(1).split('/')[0] === 'index') {
        //         httpPop.get('posting', '1', data => {
        //             //window.console.log(data)
        //             this.list = data
        //         })
        //         httpPop.get('topic', '1', data => {
        //             //window.console.log(data)
        //             this.tlist = data
        //         })
        //     }
        //     else {
        //         httpSearch.get(this.$route.query.type, this.$route.query.text, 1, data=>{
        //             if(this.$route.query.type === "posting"
        //                 || this.$route.query.type === undefined
        //                 || this.$route.query.type === "postingbytag" )
        //                 this.list = data
        //             else if(this.$route.query.type === "topic" || this.$route.query.type === undefined)
        //                 this.tlist = data
        //             else if(this.$route.query.type === "user")
        //                 this.ulist = data
        //         })
        //     }
        //     next()
        // }
    }
</script>

<style scoped>

</style>