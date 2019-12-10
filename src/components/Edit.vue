<template>
    <div>
        <Input v-model="string" type="textarea" show-word-limit placeholder="Enter here..."
               size="large" :autosize="minRows" autofocus
               style="display: inline-block; width: 60%; margin-top: 20px" />
    </div>
</template>

<script>
    export default {
        name: "Edit",
        data () {
            return {
                string: '',
                minRows: {
                    minRows: 20
                }
            }
        }
    }
</script>

<style scoped>

</style>