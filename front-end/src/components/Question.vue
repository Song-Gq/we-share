<template>
    <Scroll :height="avaiHeight" style="max-width: 480px; margin: 0 0 0 20px">
        <card title="关于此贴的问答" icon="md-help" style="margin-top: 20px; margin-right: 50px;
         padding: 5px 0 0 5px; text-align: left">
            <CellGroup v-for="item in qlist" :key="item.questionIdx" style="padding: 0 0 10px 0;">
                <Cell :title="item.content" :label="item.questionerId"
                      :to="{path: 'answer', query:{ postingId: postingId, questionIdx: item.questionIdx }}"/>
            </CellGroup>
<!--            <Timeline>
                <TimelineItem v-for="item in qlist" :key="item.questionIdx" style="text-align: left;">
                    <router-link :to="{path: 'answer', query:{ postingId: postingId, questionIdx: item.questionIdx }}">
                        <p class="question">{{item.content}}</p>
                    </router-link>
                    &lt;!&ndash;<a :href="item.content"><p class="questioner">{{item.questionerId}}</p></a>&ndash;&gt;
                    <p class="questioner">{{item.questionerId}}</p>
                </TimelineItem>
            </Timeline>-->
            <!--                <List item-layout="vertical">
            &lt;!&ndash;                    <ListItem :key="postingId" style="text-align: left">
                                    <Icon type="md-help" />
                                    关于此贴的问答
                                </ListItem>&ndash;&gt;
                                <ListItem v-for="item in qlist" :key="item.contentIdx" style="text-align: left">
                                    <ListItemMeta :description="item.time" />
                                    {{ item.content }}
                                </ListItem>
                            </List>-->
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
        name: "Question",
        data() {
            return {
                qlist: [
                    {
                        questionIdx: '1',
                        time: '2019/12/10 14:53:12.00',
                        content: 'This is the question, this is the question, this is the question, this is the question.',
                        questionerId: 'q id 1'
                    },
                    {
                        questionIdx: '2',
                        time: '2019/12/10 14:53:13.11',
                        content: 'This is the question, this is the question, this is the question, this is the question.',
                        questionerId: 'q id 2'
                    },
                    {
                        questionIdx: '3',
                        time: '2019/12/10 14:53:14.22',
                        content: 'This is the question, this is the question, this is the question, this is the question.',
                        questionerId: 'q id 3'
                    },
                    {
                        questionIdx: '4',
                        time: '2019/12/10 14:53:15.33',
                        content: 'This is the question, this is the question, this is the question, this is the question.',
                        questionerId: 'q id 4'
                    },
                    {
                        questionIdx: '5',
                        time: '2019/12/10 14:53:16.44',
                        content: 'This is the question, this is the question, this is the question, this is the question.',
                        questionerId: 'q id 5'
                    }
                ],
                avaiHeight: document.documentElement.clientHeight - 60,
                loadingQlist: false
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
                                content: 'This is the question, this is the question, this is the question, this is the question.',
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
            }
        },
        computed: {
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
    .question{
        font-size: 14px;
        font-weight: bold;
    }
    .questioner{
        /*        padding-left: 5px;*/
    }
</style>