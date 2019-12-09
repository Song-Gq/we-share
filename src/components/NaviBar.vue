<template>
    <div>
        <Menu mode="horizontal" :theme="theme" active-name="1">
            <MenuItem name="logo">
                LOGO
            </MenuItem>
            <MenuItem name="new-posting">
                <Icon type="ios-paper" />
                发布新帖
            </MenuItem>
            <MenuItem name="login">
                <Icon type="ios-people" />
                登录/注册
            </MenuItem>
            <Submenu name="search">
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
            </Submenu>
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
                theme: 'primary',
                searchType: '帖子'
            }
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>