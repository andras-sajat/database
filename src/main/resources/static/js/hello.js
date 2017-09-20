angular.module("app",[]).config(function ($httpProvider) {
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }).controller("home", function($http) {
        var self = this;

        self.refresh = function(){
            $http.get("/greeting").then(function(response){
                if(response.data) {
                    self.greeting = response.data;
                    self.hide = true;
                } else {
                    self.hide = false;
                }
            });
        };
        
        $http.get("/rest/users/all").then(function(response){
        	self.data=response.data;
        	self.refresh()
        });


        self.refresh();
        self.credentials = {};
        self.login = function () {
            $http.get('/greeting', {
                headers : { authorization : "Basic "
                + btoa(self.credentials.username
                    + ":" + self.credentials.password)
                }}).then( function (response) {
                    self.greeting = response.data;
                    self.hide = true;
            })
            $http.get("/rest/users/all").then(function(response){
            	self.data=response.data;
            	self.refresh();
            });
        };

        self.logout = function() {
            $http.post("/logout").then(function() {
                self.hide = false;
                self.greeting = {};
            });
        };
        
        self.commitNewUser = function() {
             
            var xhr = new XMLHttpRequest();
            var url = "/rest/users/load";
            xhr.open("POST", url, true);
            xhr.setRequestHeader("Content-type", "application/json");
            
            xhr.send("{\"id\": null, \"name\": \""+document.newuser.name.value+"\"}");
            
            $http.get("/rest/users/all").then(function(response){
            	self.data=response.data;
            	self.refresh();
            });
        };
        
        
});