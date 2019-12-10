<template>
    <div>
        <Input v-model="string" type="textarea" show-word-limit placeholder="Enter here..."
               size="large" :autosize="minRows" autofocus
               style="max-width: 1200px; margin: 30px auto 0 auto" />
    </div>
</template>

<script>
    export default {
        name: "Edit",
        data () {
            return {
                string: '',
                minRows: {
                    minRows: 25
                }
            }
        }
    }
</script>

<style scoped>

</style>