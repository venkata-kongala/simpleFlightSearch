(function () {
    angular.module("myApp", [
        'ui.grid',
        'ui.grid.edit'
    ])
        .controller('MainController', function ($scope, $http) {
            $scope.myData = [];
            $scope.flightNumber = null;
            $scope.origin = null;
            $scope.destination = null;
            $scope.flightDate = null;


            $scope.txnGrid = {
                data: 'myData',
                columnDefs: [
                    {field: "flightNumber", label: 'Flight Number'},
                    {field: "carrier", display: 'Carrier'},
                    {field: "origin", display: 'Origin'},
                    {field: "departure", display: 'Departure',},
                    {field: "destination", display: 'Destination'},
                    {field: "arrival", display: 'Arrival'},
                    {field: "aircraft", display: 'Aircraft'},
                    {field: "distance", display: 'Distance'},
                    {field: "travelTime", display: 'Travel Time'},
                    {field: "status", display: 'Status'},
                ]
            };


            $scope.searchFlight = function () {

                function getDatestr(date){
                    var yr = ($scope.flightDate.getYear()+1900);
                    var m = ($scope.flightDate.getMonth()+1);
                    if(m<10){
                        m='0'+ m;
                    }
                    var d = ($scope.flightDate.getDate());
                    if(d<10){
                        d='0'+ d;
                    }
                    return (yr+"-"+m+"-"+d);
                }
                $scope.hasErr = false;
                $scope.errorMsg = null;
                var url = "flight/search?";
                if ($scope.flightDate) {
                    url += 'date=' + getDatestr($scope.flightDate) + "&";
                }
                if ($scope.origin || $scope.destination) {
                    url += 'origin=' + $scope.origin + "&destination=" + $scope.destination;
                }
                if ($scope.flightNumber) {
                    url += 'flightNumber=' + $scope.flightNumber;
                }

                $http.get(url).then(function (resp) {
                    console.log(resp);
                    $scope.myData = [];
                    resp.data.forEach(function(e){
                        $scope.myData.push(e);
                    });

                },function(err){
                    console.log("error");
                    console.log(err);
                    $scope.hasErr = true;
                    $scope.errorMsg = err.data.message;
                })
            }
        });
})();