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
                        登录/注册
                    </template>
                    <MenuGroup title="用户账户">
                        <MenuItem name="login" to="login">
                            登录
                        </MenuItem>
                        <MenuItem name="register" to="register">
                            注册
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
                            return '话题ID：' + this.$route.query.text
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
                this.$nextTick(() => {
                    this.active = this.$route.path.substr(1).split('/')[0]
                    this.$refs.menu.updateActiveName()

                    if(name === 'search' && this.$route.path.substr(1).split('/')[0] !== 'search') {
                        this.active = 'logo'
                        this.$refs.menu.updateActiveName()
                    }
                })
            }
        },
        mounted() {
            this.$nextTick(()=>{
                this.active = this.$route.path.substr(1).split('/')[0]
                this.$refs.menu.updateActiveName()
            })
        }
    }
</script>

<style scoped>

</style>