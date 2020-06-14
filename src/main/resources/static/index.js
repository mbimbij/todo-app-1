var app = new Vue({
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

Vue.component('button-create-item', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button id="createItem" type="button" class="btn btn-primary">Create</button>'
})