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
                    return (this.items.push(response.data));
                })
                .catch(reason => (alert(reason)))
        },
        deleteItem: function (itemId) {
            axios
                .delete('http://localhost:8080/item/'+itemId)
                .then(response => {
                    var removeIndex = this.items.map(function(item) { return item.id; }).indexOf(itemId);
                    this.items.splice(removeIndex, 1);
                })
                .catch(reason => (alert(reason)))
        },
        changeState: function (item, newState) {
            axios
                .post('http://localhost:8080/item/'+item.id+'/changeState/'+newState, null)
                .then(response => {
                    item.state = newState
                })
                .catch(reason => (alert(reason)))
        }
    }
})

feather.replace()