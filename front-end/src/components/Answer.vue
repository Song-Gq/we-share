<template>
    <Scroll :height="avaiHeight" style="max-width: 480px; margin: 0 0 0 20px">
        <card style="margin-top: 20px; margin-right: 50px;
         padding: 5px 0px 0px 20px; text-align: left">
            <p slot="title">
                <icon type="md-person" />
                {{questionIdx}}
            </p>
            <p slot="extra">
                <Button type="primary" shape="circle" icon="ios-arrow-back" :to="{path: 'question', query:{ postingId: postingId}}" />
            </p>
            <List item-layout="vertical">
                <ListItem v-for="item in qlist" :key="item.contentIdx" style="text-align: left">
                    <ListItemMeta :description="item.time" :avatar="avatarDir" />
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
</template>

<script>
    export default {
        name: "Answer",
        data() {
            return {
                qlist: [
                    {
                        questionIdx: '1',
                        time: '2019/12/10 14:53:12.00',
                        content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        questionerId: 'q id 1'
                    },
                    {
                        questionIdx: '2',
                        time: '2019/12/10 14:53:13.11',
                        content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        questionerId: 'q id 2'
                    },
                    {
                        questionIdx: '3',
                        time: '2019/12/10 14:53:14.22',
                        content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        questionerId: 'q id 3'
                    },
                    {
                        questionIdx: '4',
                        time: '2019/12/10 14:53:15.33',
                        content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        questionerId: 'q id 4'
                    },
                    {
                        questionIdx: '5',
                        time: '2019/12/10 14:53:16.44',
                        content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        questionerId: 'q id 5'
                    }
                ],
                avaiHeight: document.documentElement.clientHeight - 60,
                loadingQlist: false,
                avatarDir: require("../assets/avatar/2.jpg")
            }
        },
        methods: {
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
                                content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                                questionerId: 'q id ' + idx
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
            toLoadingQlist(){
                this.loadingQlist = true
                this.handleReachBottomQlist()
            },
            back() {
                this.$router.push({path: 'question', query:{ postingId: this.postingId}})
            }
        },
        computed: {
            questionIdx: function () {
                return this.$route.query.questionIdx
            },
            postingId: function () {
                return this.$route.query.postingId
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
        }
    }
</script>

<style scoped>

</style>