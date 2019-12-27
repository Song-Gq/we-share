<template>
    <div style="position: relative; z-index: 1">
        <Menu mode="horizontal" :theme="theme" @on-select="outFocus" :active-name="active" ref="menu">
            <MenuItem name="index" to="/index">
                WESHARE
            </MenuItem>
            <MenuItem name="edit" to="/edit">
                <Icon type="md-paper" />
                发布新帖
            </MenuItem>
            <MenuItem name="search" style="width: 30%">
                <i-input v-model="searchText" style="margin-top: 14px" @on-enter="search">
                    <Select v-model="searchType" slot="prepend" style="width: 80px">
                        <Option value="posting">帖子</Option>
                        <Option value="topic">话题</Option>
                        <Option value="user">用户</Option>
                    </Select>
                    <Button slot="append" icon="md-search" @click="search"></Button>
                </i-input>
            </MenuItem>
            <MenuItem name="login" style="float: right">
                <Submenu name="login">
                    <template slot="title">
                        <Icon type="md-person" />
                        {{loginOrName}}
                    </template>
                    <MenuGroup title="用户账户">
                        <MenuItem name="login">
                            {{loginOrlogout}}
                        </MenuItem>
                        <MenuItem name="register">
                            {{registerOrPersonal}}
                        </MenuItem>
                    </MenuGroup>
                </Submenu>
            </MenuItem>
        </Menu>
    </div>
</template>

<script>

    export default {
        name: "NaviBar",
        data () {
            return {
                theme: 'light',
                searchType: 'posting',
                searchTextData: '',
                active: 'logo'
            }
        },
        computed: {
            searchText: {
                get() {
                    if (this.$route.query.text !== undefined) {
                        if (this.$route.query.type == 'postingbytag')
                            return '话题：' + this.$route.query.text
                        return this.$route.query.text
                    }
                    else if(this.searchTextData !== "")
                        return this.searchTextData
                    else
                        return ""
                },
                set(val) {
                    this.searchTextData = val
                }
            },
            loginOrName: function () {
                if (this.$root.hasLogin)
                    return 'ID: ' + this.$root.userId
                return '登录/注册'
            },
            loginOrlogout: function () {
                if (this.$root.hasLogin)
                    return '登出'
                return '登录'
            },
            registerOrPersonal: function () {
                if (this.$root.hasLogin)
                    return '个人主页'
                return '注册'
            }
        },
        methods: {
            search() {
/*                window.console.log('textdata' + this.searchTextData)
                if(this.searchTextData !== "")
                    this.$router.push({path: '/search', query:{ type: this.searchType, text: this.searchTextData }})*/
                if(this.searchTextData !== "" && this.searchTextData !== undefined){
                    // httpSearch.get(this.searchType, this.searchTextData, 1, data=>{
                    //     this.$router.push({path: '/search', query:{ type: this.searchType,
                    //             text: this.searchTextData, list: data}})
                    // })
                    this.$router.push({path: '/search', query:{ type: this.searchType, text: this.searchTextData }})
                }
                else if (this.searchText !== undefined && this.searchText !== "") {
                    // httpSearch.get(this.searchType, this.searchText, 1, data=>{
                    //     this.$router.push({path: '/search', query:{ type: this.searchType,
                    //             text: this.searchText, list: data}})
                    // })
                    this.$router.push({path: '/search', query:{ type: this.searchType, text: this.searchText }})
                }
            },
            outFocus(name) {
                if (name === 'login') {
                    if (this.$root.hasLogin === false)
                        this.$router.push({path: '/login'})
                    else {
                        this.$root.hasLogin = false
                        this.$root.userId = false
                        this.$router.push({path: '/index'})
                        this.$Message.warning('已登出')
                    }
                }
                else if (name == 'register') {
                    if (this.$root.hasLogin === false)
                        this.$router.push({path: '/register'})
                    else {
                        this.$router.push({path: '/personalpage', query:{ userId: this.$root.userId }})
                    }
                }

                this.$nextTick(() => {
                    this.active = this.$route.path.substr(1).split('/')[0]
                    this.$refs.menu.updateActiveName()

                    if(name === 'search' && this.$route.path.substr(1).split('/')[0] !== 'search') {
                        this.active = 'logo'
                        this.$refs.menu.updateActiveName()
                    }
                })
            },
            // checkLogin() {
            //     if (this.$root.hasLogin === true) {
            //
            //     }
            // }
        },
        mounted() {
            this.$nextTick(()=>{
                this.active = this.$route.path.substr(1).split('/')[0]
                this.$refs.menu.updateActiveName()
            })
        },
        // watch: {
        //     '$route'() {
        //         // 对路由变化作出响应...
        //         this.outFocus()
        //     },
        //     '$root.hasLogin'() {
        //         this.checkLogin()
        //     }
        // },
    }
</script>

<style scoped>

</style>