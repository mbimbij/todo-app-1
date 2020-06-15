Vue.component('button-create-item', {
    data() {
        return{
            name: null
        }
    },
    template: '#createItem'
})

new Vue({
    el: '#app',
    data() {
        return {
            items: [],
            newItemName: null
        }
    },
    mounted() {
        axios
            .get('http://localhost:8080/listItems')
            .then(response => (this.items = response.data.items))
            .catch(reason => (alert(reason)))
    },
    methods:{
        createItem: function() {
            var data = {name: this.newItemName, state: 'todo'};
            axios
                .post('http://localhost:8080/createItem', data)
                .then(response => {
                    this.newItemName = '';
                    return (this.items.push(data));
                })
                .catch(reason => (alert(reason)))
        },
        deleteItem: function (item) {
            axios
                .post('http://localhost:8080/deleteItem', data)
                .then(response => {
                    this.items.remove(function(el) { return el.name === item.name; });
                })
                .catch(reason => (alert(reason)))
        }
    }
})

feather.replace()