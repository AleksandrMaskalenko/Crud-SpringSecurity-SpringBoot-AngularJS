angular.module('myApp')

    .controller('EditSongsController', function ($scope, $http, $rootScope, $timeout) {

        $scope.name = $rootScope.nameScope;
        $scope.duration = $rootScope.durationScope;
        $scope.date = $rootScope.dateScope;
        $scope.album = $rootScope.albumScope;


        $(document).ready(function() {
            $.fn.select2.defaults.set("minimumResultsForSearch","Infinity");
            $(".select--filter").select2({
                minimumResultsForSearch: 2
            }).on('change',function(){$('.select--filter--value').text($(".select--filter").val());});
            $(".select--no-filter").select2();
        }).on('change',function(){$('.select--no-filter--value').text($(".select--no-filter").val());});

        $('#date').mask("9999");
        $('#duration').mask("99:99");


        $scope.editAuthor = function () {
            $scope.editAuthorName = true;
        };


        $scope.edit = function () {
            var authorObjUpd = {
                id: $rootScope.authorScope.id,
                name: $rootScope.authorScope.name
            };

            $http.post('http://localhost:8080/author/add', authorObjUpd).success(function () {
                $scope.message = "Author updated successfully!";
                $timeout(function(){$scope.message = '';}, 3000);
                $scope.editAuthorName = null;
            });
        };


        $scope.updateSong = function () {

            var songObjUpd = {
                id: $rootScope.idScope,
                name: $scope.name,
                author: $scope.author,
                duration: $scope.duration,
                date: $scope.date,
                album: $scope.album,
                content: $rootScope.contentScope
            };

            $http.post('http://localhost:8080/song/update', songObjUpd).success(function () {
                $scope.message = "Song updated successfully!";
                $timeout(function(){$scope.message = '';}, 3000);
            });

        };
    });