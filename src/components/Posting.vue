<template>
    <div style="display: flex; justify-content: space-between">
        <div dir="rtl">
            <Scroll :height="avaiHeight" style="max-width: 1200px; margin: 0 20px 0 0">
                <div dir="ltr">
                    <card style="margin-top: 20px; margin-left: 10px; padding: 0 20px">
                        <List item-layout="vertical">
                            <ListItem :key="postingId" style="text-align: left; display: flex">
                                <ListItemMeta :avatar="posting.avatar" :title="posting.title" :description="posting.topic" />
                                <template slot="action">
                                    <li>
                                        <Icon type="md-add" /> 关注用户
                                    </li>
                                    <li>
                                        <Icon type="md-star" /> 收藏帖子
                                    </li>
                                </template>
                            </ListItem>
                            <ListItem v-for="item in list" :key="item.contentIdx" style="text-align: left">
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
                </div>
            </Scroll>
        </div>
        <Scroll :height="avaiHeight" style="max-width: 600px; margin: 0 0 0 20px">
            <card style="margin-top: 20px; margin-right: 10px; padding: 0 20px">
                <List item-layout="vertical">
                    <ListItem :key="postingId" style="text-align: left">
                        <Icon type="md-help" />
                        关于此贴的问答
                    </ListItem>
                    <ListItem v-for="item in qlist" :key="item.contentIdx" style="text-align: left">
                        <ListItemMeta :description="item.time" />
                        {{ item.content }}
                    </ListItem>
                </List>
            </card>
            <Button type="primary" :loading="loadingQlist" icon="ios-more" @click="toLoadingQlist"
                    shape="circle" style="margin: 20px auto">
                <span v-if="!loadingQlist">加载更多</span>
                <span v-else>Loading...</span>
            </Button>
        </Scroll>
    </div>
</template>

<script>
    export default {
        name: "Posting",
        data () {
            return {
                posting: {
                    avatar: require('../assets/avatar/1.jpg'),
                    title: 'posting title',
                    topic: 'topic 1 2 3',
                    pic: require('../assets/pic/1.jpg')
                },
                list: [
                    {
                        contentIdx: '1',
                        time: '2019/12/10 14:53:12.00',
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/1.jpg'),
                    },
                    {
                        contentIdx: '2',
                        time: '2019/12/10 14:53:13.11',
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/2.jpg'),
                    },
                    {
                        contentIdx: '3',
                        time: '2019/12/10 14:53:14.22',
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/3.jpg'),
                    },
                    {
                        contentIdx: '4',
                        time: '2019/12/10 14:53:15.33',
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/4.jpg'),
                    },
                    {
                        contentIdx: '5',
                        time: '2019/12/10 14:53:16.44',
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/5.jpg'),
                    }
                ],
                qlist: [
                    {
                        questionIdx: '1',
                        time: '2019/12/10 14:53:12.00',
                        content: 'This is the question, this is the question, this is the question, this is the question.'
                    },
                    {
                        questionIdx: '2',
                        time: '2019/12/10 14:53:13.11',
                        content: 'This is the question, this is the question, this is the question, this is the question.'
                    },
                    {
                        questionIdx: '3',
                        time: '2019/12/10 14:53:14.22',
                        content: 'This is the question, this is the question, this is the question, this is the question.'
                    },
                    {
                        questionIdx: '4',
                        time: '2019/12/10 14:53:15.33',
                        content: 'This is the question, this is the question, this is the question, this is the question.'
                    },
                    {
                        questionIdx: '5',
                        time: '2019/12/10 14:53:16.44',
                        content: 'This is the question, this is the question, this is the question, this is the question.'
                    }
                ],
                avaiHeight: document.documentElement.clientHeight - 60,
                loading: false,
                loadingQlist: false
            }
        },
        computed: {
            postingId: function () {
                return this.$route.query.postingId
            }
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
                                contentIdx: idx,
                                time: "This is time " + idx,
                                content: 'This is the content, this is the content, this is the content, this is the content.',
                                pic: require('../assets/pic/' + idx + '.jpg'),
                            });
                        }
                        resolve();
                        this.loading = false
                    }, 2000);
                });
            },
            handleReachBottomQlist () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        const last = this.qlist.length;
                        var idx;
                        for (let i = 1; i < 6; i++) {
                            idx = last + i;
                            this.qlist.push({
                                questionIdx: idx,
                                time: "This is time " + idx,
                                content: 'This is the question, this is the question, this is the question, this is the question.'
                            });
                        }
                        resolve();
                        this.loadingQlist = false
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
            },
            toLoadingQlist(){
                this.loadingQlist = true
                this.handleReachBottomQlist()
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