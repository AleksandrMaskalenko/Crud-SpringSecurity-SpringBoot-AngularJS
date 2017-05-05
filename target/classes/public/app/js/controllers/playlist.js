angular.module('myApp')

.controller('PlaylistController', function ($scope, $http, $rootScope, AuthService) {

    $scope.user = AuthService.user;

    $http.get('http://localhost:8080/playlist/' + $scope.user.principal.id).then(function (response) {
        $scope.songs = response.data;
    });



    $scope.deleteSongFromPlaylist = function (song) {
        var idx = $scope.songs.indexOf(song);
        $http.delete('http://localhost:8080/playlist/delete/' + song.id).then($scope.songs.splice(idx, 1));
    };

    // $scope.playSong = function () {
    //     $http.get('http://localhost:8080/play').then(function (responce) {
    //         $scope.songPlay = responce.data;
    //     });
    // };

    $scope.songDetails = function (song) {

        $rootScope.songId = song.id;
        $rootScope.songName = song.name;
        $rootScope.songAuthor = song.author.name;
        $rootScope.songDuration = song.duration;
        $rootScope.songDate = song.date;
        $rootScope.songAlbum = song.album;

    };


});