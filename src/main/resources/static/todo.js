angular.module('todoApp', [])
  .controller('TodoListController', function($scope, $http) {
     var todoList = this;
     todoList.error = {show:false,message:""};

    todoList.todos = [];
    todoList.sortBy = 'createDate';
    todoList.reverse = true;
    todoList.categories = [];

    todoList.sort = function(propertyName) {
                        todoList.reverse = (todoList.sortBy === propertyName) ? !todoList.reverse : false;
                        todoList.sortBy = propertyName;
                      };

    todoList.getRemainingTodos = function() {
        $http({
          method: 'GET',
          url: '/remainingTodos'
        }).then(function successCallback(response) {
             todoList.todos = response.data;
          }, function errorCallback(response) {
          });
    }

    todoList.getCategories = function() {
        $http({
          method: 'GET',
          url: '/category'
        }).then(function successCallback(response) {
             todoList.categories = response.data;
          }, function errorCallback(response) {
          });
    }

    todoList.saveNewTask = function(data) {
        data.categories = todoList.categories.filter((cat) => data.categories.includes(cat.category));

        $http.post('/todo',
                    data)
         .then(
            function (response) {
               todoList.getRemainingTodos();
               $scope.newTodo = "";
               $scope.newCategories = [];
            }, function(response){
                   todoList.error.show=true;
                   todoList.error.message = response.data.message ;
                   console.log(response.data)
            });
    };

    todoList.completeToDo = function(todoID) {
          $http.post('/todo/complete/'+todoID)
           .then(
              function (response) {
                 todoList.getRemainingTodos();
              }, function(response){
                     todoList.error.show=true;
                     todoList.error.message = response.data.message ;
                     console.log(response.data)
              });
      };
    todoList.filterByCategory = function(filterCategory){
        todoList.todos = todoList.todos.filter((todo) => todo.categories.filter((cat) => cat.category === filterCategory).length > 0);
    }

    todoList.getRemainingTodos();
    todoList.getCategories();
  })
  .filter('fromNow', function() {
    return function(date) {
      return moment(date).fromNow();
    };
  });