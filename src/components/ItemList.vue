<template>
    <Scroll :height="avaiHeight" style="position: relative; z-index: 0">
        <card style="max-width: 1200px; margin: 20px auto 0 auto; padding: 0 20px">
            <Tabs v-model="tabType" :animated="false">
                <TabPane :label="popularPosting" :disabled="postingTab" name="posting">
                    <List item-layout="vertical">
                        <ListItem v-for="item in list" :key="item.postingId" style="text-align: left">
                            <ListItemMeta :avatar="item.avatar" >
                                <template slot="description">
                                    <tag color="blue">{{item.topic}}</tag>
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
                                    <Icon type="md-eye" /> 浏览量
                                </li>
                                <li>
                                    <Icon type="md-star" /> 收藏量
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
                                      :to="{path: '/search', query:{ type: 'posting', text: item.tagName }}" />
                            </CellGroup>
<!--                        </Card>-->
                    </div>
<!--
                    <Row type="flex" justify="space-around" class="code-row-bg" style="padding: 10px" >
                        <i-col span="3">
                            <Button shape="circle" >
                                tag
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                tag12345678
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                tag3333
                                <Icon type="ios-arrow-forward" />
                            </Button>
                        </i-col>
                        <i-col span="3">
                            <Button shape="circle" >
                                11111111111111111111111tag
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                123tag
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                tag000000000
                                <Icon type="ios-arrow-forward" />
                            </Button>
                        </i-col>
                        <i-col span="3">
                            <Button shape="circle" >
                                tag234567890-
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                tag789098765
                                <Icon type="ios-arrow-forward" />
                            </Button>
                            <Button shape="circle" >
                                tag871
                                <Icon type="ios-arrow-forward" />
                            </Button>
                        </i-col>
                    </Row>
-->
                </TabPane>
                <TabPane :label="popularUser" :disabled="userTab" name="user">

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
    export default {
        name: "ItemList",
        data () {
            return {
                list: [
                    {
                        postingId: 'posting id 1',
                        title: 'This is title 1',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/1.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/1.jpg'),
                    },
                    {
                        postingId: 'posting id 2',
                        title: 'This is title 2',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/2.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/2.jpg'),
                    },
                    {
                        postingId: 'posting id 3',
                        title: 'This is title 3',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/3.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/3.jpg'),
                    },
                    {
                        postingId: 'posting id 4',
                        title: 'This is title 4',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/4.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/4.jpg'),
                    },
                    {
                        postingId: 'posting id 5',
                        title: 'This is title 5',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/5.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/5.jpg'),
                    }
                ],
                tlist: [
                    {
                        tagName: 'tag11111111111',
                        tagId: '1'
                    },
                    {
                        tagName: 'tag11111111111',
                        tagId: '2'
                    },                    {
                        tagName: 'tag11122222222222211111111',
                        tagId: '3'
                    },                    {
                        tagName: 'tag11113331111111',
                        tagId: '4'
                    },                    {
                        tagName: 'tag111444444444411111111',
                        tagId: '5'
                    },                    {
                        tagName: 'tag111155555555555555555555555551111111',
                        tagId: '6'
                    },                    {
                        tagName: 'tag11111111111',
                        tagId: '7'
                    },                    {
                        tagName: 'tag11111111111',
                        tagId: '8'
                    },                    {
                        tagName: 'tag11111111111',
                        tagId: '9'
                    },                    {
                        tagName: 'tag11111111111',
                        tagId: '10'
                    },
                ],
                avaiHeight: document.documentElement.clientHeight - 60,
                loading: false,
                tabTypeData: ""
            }
        },
        computed: {
            tabType: {
                get() {
                    this.pageResize()
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
                if(this.$route.query.type === "posting" || this.$route.query.type === undefined) {
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
                        const last = this.list.length;
                        var idx;
                        for (let i = 1; i < 6; i++) {
                            idx = last + i;
                            if (this.tabType === 'posting') {
                                this.list.push({
                                    postingId: 'posting id ' + idx,
                                    title: "This is title " + idx,
                                    topic: 'Topic1 Topic2 Topic3',
                                    avatar: require('../assets/avatar/' + idx + '.jpg'),
                                    content: 'This is the content, this is the content, this is the content, this is the content.',
                                    pic: require('../assets/pic/' + idx + '.jpg'),
                                });
                            }
                            else if (this.tabType === 'topic') {
                                this.tlist.push({
                                    tagName: 'newtagname' + idx,
                                    tagId: 'newtagid' + idx
                                });
                            }
                            else if (this.tabType === 'user') {
                                this.list.push({

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
            toLoading(){
                this.loading = true
                this.handleReachBottom()
            }
        },
        mounted(){
            let _this = this;
            window.onresize = ()=>{
                _this.pageResize();
            }
        },
        destroyed(){
            window.onresize = null;
        }
    }
</script>

<style scoped>

</style>