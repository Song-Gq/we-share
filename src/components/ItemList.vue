<template>
    <Scroll :on-reach-bottom="handleReachBottom" :height="avaiHeight">
        <card style="width:60%; margin: 20px auto">
            <List item-layout="vertical">
                <ListItem v-for="item in list" :key="item.title" style="text-align: left">
                    <ListItemMeta :avatar="item.avatar" :title="item.title" :description="item.topic" />
                    {{ item.content }}
                    <template slot="action">
                        <li>
                            <Icon type="ios-eye-outline" /> 浏览量
                        </li>
                        <li>
                            <Icon type="ios-star-outline" /> 收藏量
                        </li>
                    </template>
                    <template slot="extra" style="max-height: fit-content">
                        <img :src="item.pic" style="max-height: 200px">
                    </template>
                </ListItem>
            </List>
        </card>
    </Scroll>
</template>

<script>
    export default {
        name: "ItemList",
        data () {
            return {
                list: [
                    {
                        title: 'This is title 1',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/1.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/1.jpg'),
                    },
                    {
                        title: 'This is title 2',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/2.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/2.jpg'),
                    },
                    {
                        title: 'This is title 3',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/3.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/3.jpg'),
                    },
                    {
                        title: 'This is title 4',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/4.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/4.jpg'),
                    },
                    {
                        title: 'This is title 5',
                        topic: 'Topic1 Topic2 Topic3',
                        avatar: require('../assets/avatar/5.jpg'),
                        content: 'This is the content, this is the content, this is the content, this is the content.',
                        pic: require('../assets/pic/5.jpg'),
                    }
                ],
                avaiHeight: 600
            }
        },
        methods: {
            handleReachBottom () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        const last = this.list.length;
                        var idx;
                        for (let i = 1; i < 6; i++) {
                            idx = last + i;
                            this.list.push({
                                title: "This is title " + idx,
                                topic: 'Topic1 Topic2 Topic3',
                                avatar: require('../assets/avatar/' + idx + '.jpg'),
                                content: 'This is the content, this is the content, this is the content, this is the content.',
                                pic: require('../assets/pic/' + idx + '.jpg'),
                            });
                        }
                        resolve();
                    }, 2000);
                });
            }
        }
    }
</script>

<!--<template>
    <Scroll :on-reach-bottom="handleReachBottom">
        <Card dis-hover v-for="(item, index) in list1" :key="index" style="margin: 32px 0">
            Content {{ item }}
        </Card>
    </Scroll>
</template>-->
<!--<script>
    export default {
        data () {
            return {
                list1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
            }
        },
        methods: {
            handleReachBottom () {
                return new Promise(resolve => {
                    setTimeout(() => {
                        const last = this.list1[this.list1.length - 1];
                        for (let i = 1; i < 11; i++) {
                            this.list1.push(last + i);
                        }
                        resolve();
                    }, 2000);
                });
            }
        }
    }
</script>-->

<style scoped>

</style>