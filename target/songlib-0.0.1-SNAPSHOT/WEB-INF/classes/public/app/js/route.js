angular.module('myApp').config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/page-not-found');
	$stateProvider.state('nav', {
		abstract : true,
		url : '',
		views : {
			'nav@' : {
				templateUrl : 'app/views/nav.html',
				controller : 'NavController'
			}
		}
	}).state('login', {
		parent : 'nav',
		url : '/login',
		views : {
			'content@' : {
				templateUrl : 'app/views/login.html',
				controller : 'LoginController'
			}
		}
	}).state('users', {
		parent : 'nav',
		url : '/users',
		data : {
			role : 'ADMIN'
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/users.html',
				controller : 'UsersController'
			}
		}
	}).state('home', {
		parent : 'nav',
		url : '/',
		views : {
			'content@' : {
				templateUrl : 'app/views/song-main-lib.html',
				controller : 'SongsController'
			}
		}
	}).state('page-not-found', {
		parent : 'nav',
		url : '/page-not-found',
		views : {
			'content@' : {
				templateUrl : 'app/views/page-not-found.html',
				controller : 'PageNotFoundController'
			}
		}
	}).state('register', {
		parent : 'nav',
		url : '/register',
		views : {
			'content@' : {
				templateUrl : 'app/views/register.html',
				controller : 'RegisterController'
			}
		}
	}).state('details', {
        parent : 'nav',
        url : '/details',
        views : {
            'content@' : {
                templateUrl : 'app/views/song-details.html',
                controller : 'SongsController'
            }
        }
    }).state('new-song', {
        parent : 'nav',
        url : '/new_song',
        views : {
            'content@' : {
                templateUrl : 'app/views/song-add.html',
                controller : 'AddSongsController'
            }
        }
    }).state('edit', {
        parent : 'nav',
        url : '/edit',
        views : {
            'content@' : {
                templateUrl : 'app/views/song-edit.html',
                controller : 'EditSongsController'
            }
        }
    }).state('playlist', {
        parent : 'nav',
        url : '/playlist',
        views : {
            'content@' : {
                templateUrl : 'app/views/user-playlist.html',
                controller : 'PlaylistController'
            }
        }
    });
});
