function com_esofthead_mycollab_widgetset_MyCollabWidgetSet(){var O='bootstrap',P='begin',Q='gwt.codesvr.com.esofthead.mycollab.widgetset.MyCollabWidgetSet=',R='gwt.codesvr=',S='com.esofthead.mycollab.widgetset.MyCollabWidgetSet',T='startup',U='DUMMY',V=0,W='body',X='iframe',Y='javascript:""',Z='position:absolute; width:0; height:0; border:none; left: -1000px;',$=' top: -1000px;',_='CSS1Compat',ab='<!doctype html>',bb='',cb='<html><head><\/head><body><\/body><\/html>',db='undefined',eb=/loaded|complete/,fb='DOMContentLoaded',gb=50,hb='Chrome',ib='eval("',jb=1,kb='");',lb='script',mb='javascript',nb='moduleStartup',ob='moduleRequested',pb='head',qb='meta',rb='name',sb='com.esofthead.mycollab.widgetset.MyCollabWidgetSet::',tb='::',ub='gwt:property',vb='content',wb='=',xb='gwt:onPropertyErrorFn',yb='Bad handler "',zb='" for "gwt:onPropertyErrorFn"',Ab='gwt:onLoadErrorFn',Bb='" for "gwt:onLoadErrorFn"',Cb='#',Db='?',Eb='/',Fb=/^\w+:\/\//,Gb='img',Hb='clear.cache.gif',Ib='baseUrl',Jb='com.esofthead.mycollab.widgetset.MyCollabWidgetSet.nocache.js',Kb='base',Lb='//',Mb=/^\//,Nb=/^[a-zA-Z]+:\/\//,Ob='modernie',Pb='MSIE',Qb='Trident',Rb='yes',Sb='none',Tb='user.agent',Ub='opera',Vb='webkit',Wb='trident',Xb='safari',Yb='msie',Zb=10,$b='ie10',_b=9,ac='ie9',bc=8,cc='ie8',dc='gecko',ec='gecko1_8',fc='unknown',gc=2,hc=3,ic=4,jc=5,kc='selectingPermutation',lc='com.esofthead.mycollab.widgetset.MyCollabWidgetSet.devmode.js',mc='AC7DCFC9326F54F82746A88D45705552',nc=':1',oc=':2',pc=':3',qc=':4',rc=':5',sc=':6',tc=':',uc='.cache.js',vc='link',wc='rel',xc='stylesheet',yc='href',zc='loadExternalRefs',Ac='ratingstars/styles.css',Bc='fi_jasoft_dragdroplayouts/dragdroplayouts.css',Cc='popupbutton/popupbutton.css',Dc='tokenfield/tokenfield.css',Ec='stylecalendar.css',Fc='stylecalendarfield.css',Gc='buttongroup-widget/styles.css',Hc='styles/breadcrumb.css ',Ic='listbuilder/styles.css',Jc='easyuploads.css',Kc='cropfield/styles.css',Lc='com_vaadin_addon_timeline/styles.css',Mc='end',Nc='http:',Oc='file:',Pc='_gwt_dummy_',Qc='__gwtDevModeHook:com.esofthead.mycollab.widgetset.MyCollabWidgetSet',Rc=/^http:\/\/(localhost|127\.0\.0\.1)(:\d+)?\/.*$/,Sc='Ignoring non-whitelisted Dev Mode URL: ',Tc=':moduleBase';var o=window;var p=document;r(O,P);function q(){var a=o.location.search;return a.indexOf(Q)!=-1||a.indexOf(R)!=-1}
function r(a,b){if(o.__gwtStatsEvent){o.__gwtStatsEvent({moduleName:S,sessionId:o.__gwtStatsSessionId,subSystem:T,evtGroup:a,millis:(new Date).getTime(),type:b})}}
com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__sendStats=r;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__moduleName=S;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__errFn=null;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__moduleBase=U;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__softPermutationId=V;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__computePropValue=null;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__getPropMap=null;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__gwtInstallCode=function(){};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__gwtStartLoadingFragment=function(){return null};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__gwt_isKnownPropertyValue=function(){return false};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__gwt_getMetaProperty=function(){return null};__propertyErrorFunction=null;var s=o.__gwt_activeModules=o.__gwt_activeModules||{};s[S]={moduleName:S};var t;function u(){w();return t}
function v(){w();return t.getElementsByTagName(W)[V]}
function w(){if(t){return}var a=p.createElement(X);a.src=Y;a.id=S;a.style.cssText=Z+$;a.tabIndex=-1;p.body.appendChild(a);t=a.contentDocument;if(!t){t=a.contentWindow.document}t.open();var b=document.compatMode==_?ab:bb;t.write(b+cb);t.close()}
function A(k){function l(a){function b(){if(typeof p.readyState==db){return typeof p.body!=db&&p.body!=null}return eb.test(p.readyState)}
var c=b();if(c){a();return}function d(){if(!c){c=true;a();if(p.removeEventListener){p.removeEventListener(fb,d,false)}if(e){clearInterval(e)}}}
if(p.addEventListener){p.addEventListener(fb,d,false)}var e=setInterval(function(){if(b()){d()}},gb)}
function m(c){function d(a,b){a.removeChild(b)}
var e=v();var f=u();var g;if(navigator.userAgent.indexOf(hb)>-1&&window.JSON){var h=f.createDocumentFragment();h.appendChild(f.createTextNode(ib));for(var i=V;i<c.length;i++){var j=window.JSON.stringify(c[i]);h.appendChild(f.createTextNode(j.substring(jb,j.length-jb)))}h.appendChild(f.createTextNode(kb));g=f.createElement(lb);g.language=mb;g.appendChild(h);e.appendChild(g);d(e,g)}else{for(var i=V;i<c.length;i++){g=f.createElement(lb);g.language=mb;g.text=c[i];e.appendChild(g);d(e,g)}}}
com_esofthead_mycollab_widgetset_MyCollabWidgetSet.onScriptDownloaded=function(a){l(function(){m(a)})};r(nb,ob);var n=p.createElement(lb);n.src=k;p.getElementsByTagName(pb)[V].appendChild(n)}
com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__startLoadingFragment=function(a){return D(a)};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__installRunAsyncCode=function(a){var b=v();var c=u().createElement(lb);c.language=mb;c.text=a;b.appendChild(c);b.removeChild(c)};function B(){var c={};var d;var e;var f=p.getElementsByTagName(qb);for(var g=V,h=f.length;g<h;++g){var i=f[g],j=i.getAttribute(rb),k;if(j){j=j.replace(sb,bb);if(j.indexOf(tb)>=V){continue}if(j==ub){k=i.getAttribute(vb);if(k){var l,m=k.indexOf(wb);if(m>=V){j=k.substring(V,m);l=k.substring(m+jb)}else{j=k;l=bb}c[j]=l}}else if(j==xb){k=i.getAttribute(vb);if(k){try{d=eval(k)}catch(a){alert(yb+k+zb)}}}else if(j==Ab){k=i.getAttribute(vb);if(k){try{e=eval(k)}catch(a){alert(yb+k+Bb)}}}}}__gwt_getMetaProperty=function(a){var b=c[a];return b==null?null:b};__propertyErrorFunction=d;com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__errFn=e}
function C(){function e(a){var b=a.lastIndexOf(Cb);if(b==-1){b=a.length}var c=a.indexOf(Db);if(c==-1){c=a.length}var d=a.lastIndexOf(Eb,Math.min(c,b));return d>=V?a.substring(V,d+jb):bb}
function f(a){if(a.match(Fb)){}else{var b=p.createElement(Gb);b.src=a+Hb;a=e(b.src)}return a}
function g(){var a=__gwt_getMetaProperty(Ib);if(a!=null){return a}return bb}
function h(){var a=p.getElementsByTagName(lb);for(var b=V;b<a.length;++b){if(a[b].src.indexOf(Jb)!=-1){return e(a[b].src)}}return bb}
function i(){var a=p.getElementsByTagName(Kb);if(a.length>V){return a[a.length-jb].href}return bb}
function j(){var a=p.location;return a.href==a.protocol+Lb+a.host+a.pathname+a.search+a.hash}
var k=g();if(k==bb){k=h()}if(k==bb){k=i()}if(k==bb&&j()){k=e(p.location.href)}k=f(k);return k}
function D(a){if(a.match(Mb)){return a}if(a.match(Nb)){return a}return com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__moduleBase+a}
function F(){var f=[];var g=V;function h(a,b){var c=f;for(var d=V,e=a.length-jb;d<e;++d){c=c[a[d]]||(c[a[d]]=[])}c[a[e]]=b}
var i=[];var j=[];function k(a){var b=j[a](),c=i[a];if(b in c){return b}var d=[];for(var e in c){d[c[e]]=e}if(__propertyErrorFunc){__propertyErrorFunc(a,d,b)}throw null}
j[Ob]=function(){{var a=o.navigator.userAgent;if(a.indexOf(Pb)==-1&&a.indexOf(Qb)!=-1){return Rb}return Sb}};i[Ob]={none:V,yes:jb};j[Tb]=function(){var a=navigator.userAgent.toLowerCase();if(function(){return a.indexOf(Ub)!=-1}())return Ub;if(function(){return a.indexOf(Vb)!=-1&&a.indexOf(Wb)==-1}())return Xb;if(function(){return a.indexOf(Yb)!=-1&&p.documentMode==Zb}())return $b;if(function(){return a.indexOf(Yb)!=-1&&p.documentMode>=_b}())return ac;if(function(){return a.indexOf(Yb)!=-1&&p.documentMode>=bc}())return cc;if(function(){return a.indexOf(dc)!=-1}())return ec;return fc};i[Tb]={gecko1_8:V,ie10:jb,ie8:gc,ie9:hc,opera:ic,safari:jc};__gwt_isKnownPropertyValue=function(a,b){return b in i[a]};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__getPropMap=function(){var a={};for(var b in i){if(i.hasOwnProperty(b)){a[b]=k(b)}}return a};com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__computePropValue=k;o.__gwt_activeModules[S].bindings=com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__getPropMap;r(O,kc);if(q()){return D(lc)}var l;try{h([Sb,ec],mc);h([Rb,ec],mc+nc);h([Sb,$b],mc+oc);h([Sb,cc],mc+pc);h([Sb,ac],mc+qc);h([Sb,Ub],mc+rc);h([Sb,Xb],mc+sc);l=f[k(Ob)][k(Tb)];var m=l.indexOf(tc);if(m!=-1){g=parseInt(l.substring(m+jb),Zb);l=l.substring(V,m)}}catch(a){}com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__softPermutationId=g;return D(l+uc)}
function G(){if(!o.__gwt_stylesLoaded){o.__gwt_stylesLoaded={}}function c(a){if(!__gwt_stylesLoaded[a]){var b=p.createElement(vc);b.setAttribute(wc,xc);b.setAttribute(yc,D(a));p.getElementsByTagName(pb)[V].appendChild(b);__gwt_stylesLoaded[a]=true}}
r(zc,P);c(Ac);c(Bc);c(Cc);c(Dc);c(Ec);c(Fc);c(Gc);c(Hc);c(Ic);c(Jc);c(Kc);c(Lc);r(zc,Mc)}
B();com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__moduleBase=C();s[S].moduleBase=com_esofthead_mycollab_widgetset_MyCollabWidgetSet.__moduleBase;var H=F();if(o){var I=!!(o.location.protocol==Nc||o.location.protocol==Oc);o.__gwt_activeModules[S].canRedirect=I;function J(){var b=Pc;try{o.sessionStorage.setItem(b,b);o.sessionStorage.removeItem(b);return true}catch(a){return false}}
if(I&&J()){var K=Qc;var L=o.sessionStorage[K];if(!Rc.test(L)){if(L&&(window.console&&console.log)){console.log(Sc+L)}L=bb}if(L&&!o[K]){o[K]=true;o[K+Tc]=C();var M=p.createElement(lb);M.src=L;var N=p.getElementsByTagName(pb)[V];N.insertBefore(M,N.firstElementChild||N.children[V]);return false}}}G();r(O,Mc);A(H);return true}
com_esofthead_mycollab_widgetset_MyCollabWidgetSet.succeeded=com_esofthead_mycollab_widgetset_MyCollabWidgetSet();