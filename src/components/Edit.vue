<template>
    <div class="markdown" style="margin: 30px; position: relative; z-index: 0">
        <div class="container">
            <mavon-editor v-model="content" ref="md" @imgAdd="$imgAdd" @imgDel="$imgDel"
                          @change="change" :toolbars="toolbars" style="min-height: 500px"/>
            <Button type="primary" :loading="loading" shape="circle" icon="md-checkbox"
                    @click="submit" style="margin-top: 20px">
                提交
            </Button>
        </div>
    </div>
<!--        <Input v-model="string" type="textarea" show-word-limit placeholder="Enter here..."
               size="large" :autosize="minRows" autofocus
               style="max-width: 1200px; margin: 30px auto 0 auto" />-->
</template>

<script>
    import { mavonEditor } from 'mavon-editor'
    import 'mavon-editor/dist/css/index.css'

    export default {
        name: "Edit",
        props: [],
        data () {
            return {
                string: '',
                minRows: {
                    minRows: 25
                },
                content:'', // 输入的markdown
                html:'',    // 及时转的html
                configs: {},
                toolbars: {
                    bold: true, // 粗体
                    italic: true, // 斜体
                    header: true, // 标题
                    underline: true, // 下划线
                    strikethrough: true, // 中划线
                    mark: true, // 标记
                    superscript: true, // 上角标
                    subscript: true, // 下角标
                    quote: true, // 引用
                    ol: true, // 有序列表
                    ul: true, // 无序列表
                    link: true, // 链接
                    imagelink: true, // 图片链接
                    code: true, // code
                    table: true, // 表格
                    fullscreen: true, // 全屏编辑
                    readmodel: true, // 沉浸式阅读
                    htmlcode: true, // 展示html源码
                    help: false, // 帮助
                    /* 1.3.5 */
                    undo: true, // 上一步
                    redo: true, // 下一步
                    trash: false, // 清空
                    save: false, // 保存（触发events中的save事件）
                    /* 1.4.2 */
                    navigation: true, // 导航目录
                    /* 2.1.8 */
                    alignleft: true, // 左对齐
                    aligncenter: true, // 居中
                    alignright: true, // 右对齐
                    /* 2.2.1 */
                    subfield: true, // 单双栏模式
                    preview: true, // 预览
                },
                loading: false
            }
        },
        components: {
            mavonEditor,
        },
        methods: {
            // 绑定@imgAdd event
            $imgAdd(pos, $file){
                // 第一步.将图片上传到服务器.
                var formdata = new FormData();
                formdata.append('image', $file);
                /*axios*/({
                    url: 'server url',
                    method: 'post',
                    data: formdata,
                    headers: { 'Content-Type': 'multipart/form-data' },
                }).then((url) => {
                    // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
                    /**
                     * $vm 指为mavonEditor实例，可以通过如下两种方式获取
                     * 1. 通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
                     * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
                     */
                    this.$vm.$img2Url(pos, url);
                })
            },
            $imgDel(pos) {
                window.console.log(pos)
            },
            // 将图片上传到服务器，返回地址替换到md中
/*            $imgAdd(pos/!*, $file*!/){
                let formdata = new FormData();

                this.$upload.post('/上传接口地址', formdata).then(res => {
                    window.console.log(res.data);
                    this.$refs.md.$img2Url(pos, res.data);
                }).catch(err => {
                    window.console.log(err)
                })
            },*/
            // 所有操作都会被解析重新渲染
            change(value, render){
                // render 为 markdown 解析后的结果[html]
                this.html = render;
            },
            // 提交
            submit(){
                this.loading = true
/*                window.console.log(this.content);
                window.console.log(this.html);
                this.$message.success('提交成功，已打印至控制台！');*/
            }
        }

    }
</script>

<style scoped>

</style>