<template>
    <Scroll :height="avaiHeight" style="max-width: 480px; margin: 0 0 0 20px">
        <card style="margin-top: 20px; margin-right: 50px;
         padding: 5px 0px 0px 20px; text-align: left">
            <p slot="title">
                <icon type="md-happy" />
                问题ID：{{questionId}}
            </p>
            <p slot="extra">
                <Button type="primary" shape="circle" icon="ios-arrow-back" :to="{path: 'question', query:{ postingId: postingId}}" />
            </p>
            <List item-layout="vertical">
                <ListItem v-for="item in alist" :key="item.time" style="text-align: left">
                    <ListItemMeta :description="item.time" :avatar="item.avatarDir" />
                    {{ item.content }}
                </ListItem>
            </List>
        </card>
        <Button type="primary" :loading="loadingQlist" icon="ios-more" @click="toLoadingQlist"
                shape="circle" style="margin: 20px auto">
            <span v-if="!loadingQlist">加载更多</span>
            <span v-else>Loading...</span>
        </Button>
        <Button type="primary" icon="md-hand" @click="answer"
                shape="circle" style="margin: 20px" :disabled="!ableToAnswer">
            <span v-if="!ableToAnswer">请先登录</span>
            <span v-else>回答</span>
        </Button>
        <Modal v-model="modal" title="回答" @on-ok="submitAnswer" :loading="submitLoading">
            <Input v-model="answerText" maxlength="200" show-word-limit type="textarea"
                   placeholder="写下你的答案" />
        </Modal>
    </Scroll>
</template>

<script>
    import httpAnswer from "@/api/httpAnswerList";
    import httpSubmitAnswer from "@/api/httpSubmitAnswer"

    export default {
        name: "Answer",
        data() {
            return {
                avaiHeight: document.documentElement.clientHeight - 60,
                loadingQlist: false,
                // avatarDir: require("../assets/avatar/2.jpg"),
                alist: [],
                answerPage: 0,
                ableToAnswer: this.$root.hasLogin,
                modal: false,
                answerText: '',
                submitLoading: false
            }
        },
        methods: {
            handleReachBottomQlist () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        httpAnswer.get(this.questionId, this.answerPage + 1, data=>{
                                for (let idx in data)
                                    this.alist.push(data[idx])
                                this.answerPage += 1
                            })
                        // const last = this.qlist.length;
                        // var idx;
                        // for (let i = 1; i < 6; i++) {
                        //     idx = last + i;
                        //     this.qlist.push({
                        //         questionIdx: idx,
                        //         time: "This is time " + idx,
                        //         content: 'This is the answer, this is the answer, this is the answer, this is the answer.',
                        //         questionerId: 'q id ' + idx
                        //     });
                        // }
                        resolve();
                        this.loadingQlist = false
                    }, 10);
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
            },
            answer() {
                this.modal = true
            },
            submitAnswer() {
                if(this.answerText == ''){
                    this.$Message.error('回答内容不能为空');
                }
                else {
                    this.submitLoading = true
                    httpSubmitAnswer.get(this.questionId, this.answerText, this.$root.uesrId, data=>{
                        if(data === 200) {
                            this.$Message.success('回答成功');
                            this.answerText = ''
                        }
                        else {
                            this.$Message.error('提交失败');
                        }
                        this.submitLoading = false;
                    })
                }
            }
        },
        computed: {
            questionId: function () {
                return this.$route.query.questionId
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
        },
        created() {
            httpAnswer.get(this.questionId, 1, data=>{
                this.alist = data
                this.answerPage = 1
            })
        }
    }
</script>

<style scoped>

</style>