Vue.component('button-create-item', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<div>\n' +
        '            <button id="createItem" type="button" class="btn btn-primary" data-toggle="modal"\n' +
        '                    data-target="#exampleModal">Create\n' +
        '            </button>\n' +
        '            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"\n' +
        '                 aria-hidden="true">\n' +
        '                <div class="modal-dialog">\n' +
        '                    <div class="modal-content">\n' +
        '                        <div class="modal-header">\n' +
        '                            <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>\n' +
        '                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
        '                                <span aria-hidden="true">&times;</span>\n' +
        '                            </button>\n' +
        '                        </div>\n' +
        '                        <div class="modal-body">\n' +
        '                            ...\n' +
        '                        </div>\n' +
        '                        <div class="modal-footer">\n' +
        '                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>\n' +
        '                            <button type="button" class="btn btn-primary">Save changes</button>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>'
})

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
