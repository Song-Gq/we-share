<template>
    <div>
        <Menu mode="horizontal" :theme="theme" active-name="logo">
            <MenuItem name="logo">
                WESHARE
            </MenuItem>
            <MenuItem name="new-posting">
                <Icon type="ios-paper" />
                发布新帖
            </MenuItem>
            <MenuItem name="search-bar" style="width: 30%">
                <i-input v-model="searchText" style="margin-top: 14px">
                    <Select v-model="searchType" slot="prepend" style="width: 80px">
                        <Option value="posting">帖子</Option>
                        <Option value="topic">话题</Option>
                        <Option value="user">用户</Option>
                    </Select>
                    <Button slot="append" icon="ios-search"></Button>
                </i-input>
            </MenuItem>
<!--            <Submenu name="search">
                <template slot="title">
                    <Icon type="ios-stats" />
                    搜索
                </template>
                <MenuGroup title="搜索类型">
                    <RadioGroup v-model="searchType">
                        <MenuItem name="search-posting">
                            <Radio label="帖子"></Radio>
                        </MenuItem>
                        <MenuItem name="search-topic">
                            <Radio label="话题"></Radio>
                        </MenuItem>
                        <MenuItem name="search-user">
                            <Radio label="用户"></Radio>
                        </MenuItem>
                    </RadioGroup>
                </MenuGroup>
            </Submenu>-->
            <MenuItem name="login" style="float: right">
                <Submenu name="login">
                    <template slot="title">
                        <Icon type="ios-people" />
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
<!--        <br>
        <p>Change theme</p>
        <RadioGroup v-model="theme">
            <Radio label="light"></Radio>
            <Radio label="dark"></Radio>
            <Radio label="primary"></Radio>
        </RadioGroup>-->
    </div>
</template>

<script>
    export default {
        name: "NaviBar",
        data () {
            return {
                theme: 'light',
                searchType: 'posting',
                searchText: ''
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>