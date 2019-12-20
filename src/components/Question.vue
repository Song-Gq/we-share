<template>
    <Scroll :height="avaiHeight" style="max-width: 480px; margin: 0 0 0 20px">
        <card title="关于此贴的问答" icon="md-help" style="margin-top: 20px; margin-right: 50px;
         padding: 5px 0 0 5px; text-align: left">
            <CellGroup v-for="item in qlist" :key="item.questionId" style="padding: 0 0 10px 0;">
                <Cell :title="item.content"
                      :to="{path: 'answer', query:{ postingId: postingId, questionId: item.questionId }}">
                    <template slot="label">
                        {{item.time}}
                    </template>
                    <template slot="icon">
                        <img :src="item.avatarDir" style="width: 12px" />
                        提问者ID：{{item.questionerId}}
                    </template>
                </Cell>
            </CellGroup>
        </card>
        <Button type="primary" :loading="loadingQlist" icon="ios-more" @click="toLoadingQlist"
                shape="circle" style="margin: 20px auto">
            <span v-if="!loadingQlist">加载更多</span>
            <span v-else>Loading...</span>
        </Button>
        <Button type="primary" icon="md-help-buoy" @click="question"
                shape="circle" style="margin: 20px" :disabled="!ableToQuestion">
            <span v-if="!ableToQuestion">请先登录</span>
            <span v-else>提问</span>
        </Button>
        <Modal v-model="modal" title="提问" @on-ok="submitQuestion" :loading="submitLoading">
            <Input v-model="questionText" maxlength="200" show-word-limit type="textarea"
                   placeholder="描述你的问题" />
        </Modal>
    </Scroll>
</template>

<script>
    import httpPostingList from "@/api/httpPostingList";
    import httpSubmitQuestion from "@/api/httpSubmitQuestion";

    export default {
        name: "Question",
        data() {
            return {
                avaiHeight: document.documentElement.clientHeight - 60,
                loadingQlist: false,
                qlist: [],
                questionPage: 0,
                ableToQuestion: this.$root.hasLogin,
                modal: false,
                questionText: '',
                submitLoading: false
            }
        },
        methods: {
            handleReachBottomQlist () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        httpPostingList.get(this.postingId, 0,
                            this.questionPage + 1,data=>{
                                for (let idx in data.list)
                                    this.qlist.push(data.list[idx])
                                this.questionPage += 1
                            })
                        // const last = this.qlist.length;
                        // var idx;
                        // for (let i = 1; i < 6; i++) {
                        //     idx = last + i;
                        //     this.qlist.push({
                        //         questionIdx: idx,
                        //         time: "This is time " + idx,
                        //         content: 'This is the question, this is the question, this is the question, this is the question.',
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
            question() {
                this.modal = true
            },
            submitQuestion() {
                if(this.questionText == ''){
                    this.$Message.error('提问内容不能为空');
                }
                else {
                    this.submitLoading = true
                    httpSubmitQuestion.get(this.postingId, this.questionText, this.$root.uesrId, data=>{
                        if(data === 200) {
                            this.$Message.success('提问成功');
                            this.questionText = ''
                        }
                        else {
                            this.$Message.error('提问失败');
                        }
                        this.submitLoading = false;
                    })
                }
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
        },
        created() {
            httpPostingList.get(this.postingId, 0, 1, data=>{
                this.qlist = data.list
                this.questionPage = 1
            })
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