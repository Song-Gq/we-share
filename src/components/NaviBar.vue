<template>
    <div style="position: relative; z-index: 1">
        <Menu mode="horizontal" :theme="theme">
            <MenuItem name="logo" to="/index">
                WESHARE
            </MenuItem>
            <MenuItem name="new-posting" to="/edit">
                <Icon type="md-paper" />
                发布新帖
            </MenuItem>
            <MenuItem name="search-bar" style="width: 30%">
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
                        <MenuItem name="search-posting">
                            登录
                        </MenuItem>
                        <MenuItem name="search-topic">
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
                searchTextData: ''
            }
        },
        computed: {
            searchText: {
                get() {
                    if(this.$route.query.text === undefined)
                        return ""
                    return this.$route.query.text
                },
                set(val) {
                    this.searchTextData = val
                }
            }
        },
        methods: {
            search() {
                if(this.searchTextData !== "")
                    this.$router.push({path: '/search', query:{ type: this.searchType, text: this.searchTextData }})
            }
        }
    }
</script>

<style scoped>

</style>