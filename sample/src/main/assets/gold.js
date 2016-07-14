/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports) {

	;__weex_define__("@weex-component/5981276ed6be42dc066b00e2d0845db7", [], function(__weex_require__, __weex_exports__, __weex_module__){

	;
	const GOLD_API = '__API__URL'
	__weex_module__.exports = {
	    data: function () {return {
	        baseURL: '',
	        dir: 'examples',
	        itemList: [{
	            "tagsTitleArray": [
	                "Android",
	                "开源",
	                "Google"
	            ],
	            "category": "android",
	            "tags": {
	                "__type": "Relation",
	                "className": "Tag"
	            },
	            "hotIndex": 3839,
	            "viewsCount": 1667,
	            "subscribers": {
	                "__type": "Relation",
	                "className": "_User"
	            },
	            "collectionCount": 205,
	            "content": "带你学习了解 Google 新开元的 FlexboxLayout。",
	            "hot": false,
	            "original": true,
	            "type": "article",
	            "title": "Google 开源的 FlexboxLayout",
	            "author": "googdev",
	            "english": false,
	            "rankIndex": 0.038876303392851856,
	            "url": "https://gold.xitu.io/entry/5738887a71cfe40057038b8a/view",
	            "entryView": {
	                "__type": "Pointer",
	                "className": "EntryView",
	                "objectId": "57393a6a79bc44005c5f5b35"
	            },
	            "gfw": false,
	            "commentsCount": 0,
	            "user": {
	                "username": "googdev",
	                "avatar_large": "http://tp4.sinaimg.cn/2942550243/180/5679223253/1",
	                "avatar_hd": "http://tva1.sinaimg.cn/crop.0.0.180.180.180/af63c0e3jw1eaigs3bfuvj20500500so.jpg",
	                "objectId": "55fe1c23ddb263b5605b2c03",
	                "role": "editor",
	                "company": "薄荷科技",
	                "self_description": "微信公众账号: AndroidDeveloper「googdev」 | 个人博客: http://stormzhang.com",
	                "blogAddress": "http://stormzhang.com",
	                "totalHotIndex": 125411,
	                "postedEntriesCount": 31,
	                "collectedEntriesCount": 34,
	                "totalCollectionsCount": 4772,
	                "subscribedTagsCount": 12,
	                "followeesCount": 20,
	                "followersCount": 386
	            },
	            "subscribersCount": 0,
	            "screenshot": {
	                "__type": "File",
	                "id": "5738887a1ea4930064378e0e",
	                "name": "flexbox.jpg",
	                "url": "http://ac-mhke0kuv.clouddn.com/9fd3273139b7d8e2c3df.jpg"
	            },
	            "originalUrl": "http://mp.weixin.qq.com/s?__biz=MzA4NTQwNDcyMA==&mid=2650661681&idx=1&sn=b151aba0c5fb702492f6bbd82211988d#rd",
	            "objectId": "5738887a71cfe40057038b8a",
	            "createdAt": "2016-05-15T14:32:26.936Z",
	            "updatedAt": "2016-06-09T07:45:40.264Z",
	            "className": "Entry",
	            "createdAtString": "2016-05-15T14:32:26.936Z",
	            "updatedAtString": "2016-06-09T07:45:40.264Z",
	            "isCollected": false
	        }]
	    }},
	    created: function() {
	        this.getBaseURL()
	        this.fetchData()
	    },
	    methods: {
	        add: function() {
	            this.count++
	                this.fetchData()
	        },
	        contentFilter: function(content) {
	            return content.slice(0, 80)
	        },
	        genFetchUri: function() {
	            if (this.itemList.length) {
	                return GOLD_API + this.itemList[this.itemList.length - 1].createdAtString
	            } else {
	                var now = new Date()
	                return GOLD_API + now.toISOString()
	            }
	        },
	        fetchData: function() {
	            var stream = __weex_require__('@weex-module/stream');
	            var self = this;
	            var GET_URL = this.genFetchUri()

	            stream.sendHttp({
	                method: 'GET',
	                url: GET_URL
	            }, function(ret) {
	                console.log('get', ret);
	                self.json = ret;
	                try {
	                    if (self.itemList.length) {
	                        self.itemList = self.itemList.concat(JSON.parse(ret).data)
	                    } else {
	                        self.itemList = JSON.parse(ret).data
	                    }
	                } catch (e) {
	                    console.log(e)
	                }

	            });

	        },
	        getBaseURL: function() {
	            var bundleUrl = this.$getConfig().bundleUrl;
	            bundleUrl = new String(bundleUrl);
	            console.log('hit', bundleUrl);
	            var nativeBase;
	            var isAndroidAssets = bundleUrl.indexOf('file://assets/') >= 0;

	            var isiOSAssets = bundleUrl.indexOf('file:///') >= 0 && bundleUrl.indexOf('WeexDemo.app') > 0;
	            if (isAndroidAssets) {
	                nativeBase = 'file://assets/';
	            } else if (isiOSAssets) {
	                // file:///var/mobile/Containers/Bundle/Application/{id}/WeexDemo.app/
	                // file:///Users/{user}/Library/Developer/CoreSimulator/Devices/{id}/data/Containers/Bundle/Application/{id}/WeexDemo.app/
	                nativeBase = bundleUrl.substring(0, bundleUrl.lastIndexOf('/') + 1);
	            } else {
	                var host = 'localhost:12580';
	                var matches = /\/\/([^\/]+?)\//.exec(this.$getConfig().bundleUrl);
	                if (matches && matches.length >= 2) {
	                    host = matches[1];
	                }
	                nativeBase = 'http://' + host + '/' + this.dir + '/build/';
	            }
	            var h5Base = './index.html?page=./' + this.dir + '/build/';
	            // in Native
	            var base = nativeBase;
	            if (typeof window === 'object') {
	                // in Browser or WebView
	                base = h5Base;
	            }
	            this.baseURL = base;
	        },
	        goRead: function(url) {
	            var params = {
	                'url': this.baseURL + 'component/web-demo.js?viewurl='+ url
	            }
	            var navigator = __weex_require__('@weex-module/navigator');
	            navigator.push(params, function(e) {
	                console.log(e)
	            });
	        }
	    }
	}

	;__weex_module__.exports.template = __weex_module__.exports.template || {}
	;Object.assign(__weex_module__.exports.template, {
	  "type": "div",
	  "children": [
	    {
	      "type": "list",
	      "classList": [
	        "list"
	      ],
	      "events": {
	        "loadmore": "fetchData"
	      },
	      "attr": {
	        "loadmoreoffset": "2000"
	      },
	      "children": [
	        {
	          "type": "cell",
	          "append": "tree",
	          "classList": [
	            "row"
	          ],
	          "repeat": function () {return this.itemList},
	          "attr": {
	            "index": function () {return this.$index}
	          },
	          "children": [
	            {
	              "type": "div",
	              "classList": [
	                "item"
	              ],
	              "events": {
	                "click": function ($event) {this.goRead(this.url,$event)}
	              },
	              "children": [
	                {
	                  "type": "div",
	                  "children": [
	                    {
	                      "type": "image",
	                      "style": {
	                        "width": 800,
	                        "height": 300
	                      },
	                      "shown": function () {return this.screenshot},
	                      "attr": {
	                        "src": function () {return this.screenshot.url}
	                      }
	                    },
	                    {
	                      "type": "div",
	                      "children": [
	                        {
	                          "type": "div",
	                          "classList": [
	                            "title-box"
	                          ],
	                          "children": [
	                            {
	                              "type": "text",
	                              "classList": [
	                                "item-title"
	                              ],
	                              "attr": {
	                                "value": function () {return this.title}
	                              }
	                            },
	                            {
	                              "type": "div",
	                              "classList": [
	                                "avatar-box"
	                              ],
	                              "children": [
	                                {
	                                  "type": "image",
	                                  "classList": [
	                                    "avatar"
	                                  ],
	                                  "shown": function () {return this.user&&this.user.avatar_large},
	                                  "attr": {
	                                    "src": function () {return this.user.avatar_large}
	                                  }
	                                }
	                              ]
	                            }
	                          ]
	                        },
	                        {
	                          "type": "div",
	                          "classList": [
	                            "content-box"
	                          ],
	                          "children": [
	                            {
	                              "type": "text",
	                              "attr": {
	                                "value": function () {return this.contentFilter(this.content)}
	                              }
	                            }
	                          ]
	                        },
	                        {
	                          "type": "div",
	                          "classList": [
	                            "tag-box"
	                          ],
	                          "children": [
	                            {
	                              "type": "div",
	                              "classList": [
	                                "tag-item"
	                              ],
	                              "repeat": {
	                                "expression": function () {return this.tagsTitleArray},
	                                "value": "tag"
	                              },
	                              "children": [
	                                {
	                                  "type": "text",
	                                  "attr": {
	                                    "value": function () {return this.tag}
	                                  }
	                                }
	                              ]
	                            },
	                            {
	                              "type": "div",
	                              "classList": [
	                                "collection-box"
	                              ],
	                              "children": [
	                                {
	                                  "type": "div",
	                                  "classList": [
	                                    "collection-icon-box"
	                                  ],
	                                  "children": [
	                                    {
	                                      "type": "image",
	                                      "attr": {
	                                        "src": "http://covteam.u.qiniudn.com/heart2.png"
	                                      },
	                                      "classList": [
	                                        "collection-icon"
	                                      ]
	                                    }
	                                  ]
	                                },
	                                {
	                                  "type": "div",
	                                  "classList": [
	                                    "collection-count"
	                                  ],
	                                  "children": [
	                                    {
	                                      "type": "text",
	                                      "classList": [
	                                        "collection-count-text"
	                                      ],
	                                      "attr": {
	                                        "value": "23"
	                                      }
	                                    }
	                                  ]
	                                }
	                              ]
	                            }
	                          ]
	                        }
	                      ]
	                    }
	                  ]
	                }
	              ]
	            }
	          ]
	        }
	      ]
	    }
	  ]
	})
	;__weex_module__.exports.style = __weex_module__.exports.style || {}
	;Object.assign(__weex_module__.exports.style, {
	  "list": {
	    "width": 750,
	    "height": 1200
	  },
	  "row": {
	    "width": 750,
	    "backgroundColor": "#fafafa",
	    "flexDirection": "column"
	  },
	  "item": {
	    "backgroundColor": "#ffffff",
	    "marginBottom": 40,
	    "borderBottomWidth": 1,
	    "borderBottomColor": "#cccccc",
	    "paddingBottom": 10
	  },
	  "item-title": {
	    "fontSize": 40
	  },
	  "title-box": {
	    "position": "relative",
	    "paddingTop": 10,
	    "paddingLeft": 20,
	    "paddingRight": 70
	  },
	  "content-box": {
	    "paddingTop": 10,
	    "paddingLeft": 20,
	    "paddingRight": 10
	  },
	  "avatar-box": {
	    "position": "absolute",
	    "right": 20,
	    "top": 10,
	    "width": 50,
	    "height": 50,
	    "borderBottomLeftRadius": 25,
	    "borderBottomRightRadius": 25,
	    "borderTopLeftRadius": 25,
	    "borderTopRightRadius": 25
	  },
	  "avatar": {
	    "width": 50,
	    "height": 50
	  },
	  "tag-box": {
	    "positon": "relative",
	    "flexDirection": "row",
	    "minHeight": 70,
	    "paddingTop": 10,
	    "paddingLeft": 20,
	    "paddingRight": 80
	  },
	  "tag-item": {
	    "marginRight": 10,
	    "marginTop": 10
	  },
	  "collection-icon": {
	    "width": 50,
	    "height": 50
	  },
	  "collection-box": {
	    "position": "absolute",
	    "right": 10,
	    "flexDirection": "row"
	  },
	  "collection-count-text": {
	    "fontSize": 30,
	    "marginTop": 10,
	    "marginLeft": 10
	  }
	})
	})
	;__weex_bootstrap__("@weex-component/5981276ed6be42dc066b00e2d0845db7", {
	  "transformerVersion": "0.3.1"
	},undefined)

/***/ }
/******/ ]);