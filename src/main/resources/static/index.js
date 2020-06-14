Vue.component('button-create-item', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '#createItem'
})

new Vue({
    el: '#app',
    data() {
        return {
            items: []
        }
    },
    mounted() {
        axios
            .get('http://localhost:8080/listItems')
            .then(response => (this.items = response.data.items))
            .catch(reason => (alert(reason)))
    }
})
