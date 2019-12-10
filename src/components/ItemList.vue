<template>
    <Scroll :on-reach-bottom="handleReachBottom" :height="avaiHeight">
        <card style="max-width: 1200px; margin: 20px auto 0 auto; padding: 0 20px">
            <Tabs :value="tabType">
                <TabPane label="帖子" :disabled="postingTab" name="posting">
                    <List item-layout="vertical">
                        <ListItem v-for="item in list" :key="item.postingId" style="text-align: left">
                            <ListItemMeta :avatar="item.avatar" :description="item.topic" >
                                <template slot="title">
                                    <router-link :to="{path: 'posting', query:{ postingId: item.postingId }}">
                                        {{ item.title }}
                                    </router-link>
                                </template>
                            </ListItemMeta>
                            {{ item.content }}
                            <template slot="action">
                                <li>
                                    <Icon type="ios-eye-outline" /> 浏览量
                                </li>
                                <li>
                                    <Icon type="ios-star-outline" /> 收藏量
                                </li>
                            </template>
                            <template slot="extra" style="max-height: fit-content">
                                <img :src="item.pic" style="max-height: 200px">
                            </template>
                        </ListItem>
                    </List>
                </TabPane>
                <TabPane label="话题" :disabled="topicTab" name="topic">

                </TabPane>
                <TabPane label="用户" :disabled="userTab" name="user">

                </TabPane>
            </Tabs>
        </card>
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
                avaiHeight: document.documentElement.clientHeight - 60
            }
        },
        computed: {
            tabType: function () {
                if(this.$route.query.type === undefined)
                    return "posting"
                return this.$route.query.type
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
                            this.list.push({
                                postingId: 'posting id ' + idx,
                                title: "This is title " + idx,
                                topic: 'Topic1 Topic2 Topic3',
                                avatar: require('../assets/avatar/' + idx + '.jpg'),
                                content: 'This is the content, this is the content, this is the content, this is the content.',
                                pic: require('../assets/pic/' + idx + '.jpg'),
                            });
                        }
                        resolve();
                    }, 2000);
                });
            },
            pageResize(){
                this.$nextTick(()=>{
                    this.avaiHeight = document.documentElement.clientHeight - 60
                })
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