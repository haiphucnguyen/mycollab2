$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function YIb(a){return a.g}\nfunction $Ib(a,b){eIb(a,b);--a.i}\nfunction qsd(){ue.call(this)}\nfunction JYd(){lf.call(this);this.G=O9e}\nfunction fy(a){return (uv(),tv).Re(a,'col')}\nfunction RFc(a,b){Cfc(a.a,new $Mc(new oNc(apb),'openPopup'),XO(TO(zsb,1),iIe,1,3,[(jae(),b?iae:hae)]))}\nfunction ZIb(a,b){if(b<0){throw new dae('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new dae(DNe+b+ENe+a.i)}}\nfunction aJb(a,b){if(a.i==b){return}if(b<0){throw new dae('Cannot set number of rows to '+b)}if(a.i<b){cJb((pCb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){$Ib(a,a.i-1)}}}\nfunction bJb(a,b){OHb();lIb.call(this);fIb(this,new HIb(this));iIb(this,new RJb(this));gIb(this,new FJb(this));_Ib(this,b);aJb(this,a)}\nfunction EJb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ju(a.a,fy($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){su(a.a,a.a.lastChild)}}}\nfunction cJb(a,b,c){var d=$doc.createElement('td');d.innerHTML=URe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction psd(a){if((!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).c&&((!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v==null||Pce('',(!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v))){return (!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).a}return (!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v}\nfunction _Ib(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new dae('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){QHb(a,c,d);e=SHb(a,c,d,false);f=NJb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){aIb(a,c,d)}}}a.g=b;EJb(a.I,b,false)}\nvar Y8e={53:1,7:1,16:1,29:1,34:1,33:1,35:1,32:1,3:1},Z8e='com.vaadin.client.ui.colorpicker',$8e='background',L9e='showDefaultCaption',M9e='setColor',N9e='setOpen',O9e='v-colorpicker',W9e='v-default-caption-width';tyb(1,null,{});_.gC=function W(){return this.cZ};tyb(121,9,JIe);_.qe=function Yk(a){return Rb(this,a,(CF(),CF(),BF))};tyb(820,28,FNe);_.qe=function mIb(a){return Rb(this,a,(CF(),CF(),BF))};tyb(245,48,HNe);_.qe=function UIb(a){return Rb(this,a,(CF(),CF(),BF))};tyb(653,820,FNe,bJb);_.mh=function dJb(){var a;a=(pCb(),Hy($doc));cv(a,URe);return a};_.nh=function eJb(a){return YIb(this)};_.oh=function fJb(){return this.i};_.ph=function gJb(a,b){ZIb(this,a);if(b<0){throw new dae('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new dae(BNe+b+CNe+this.g)}};_.qh=function hJb(a){ZIb(this,a)};_.g=0;_.i=0;var mX=Zae(qHe,'Grid',653);tyb(52,598,INe);_.qe=function nJb(a){return Rb(this,a,(CF(),CF(),BF))};tyb(381,9,TNe);_.qe=function pKb(a){return Sb(this,a,(CF(),CF(),BF))};tyb(812,33,Y8e);_.Wc=function rsd(){return false};_.Zc=function ssd(){return !this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)};_.Lc=function tsd(){return !this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)};_.Nc=function usd(){gP(this._c(),54)&&cP(this._c(),54).qe(this)};_.Pc=function vsd(a){ne(this,a);if(a.Zj(gQe)){this.Nm();(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).c&&((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v==null||Pce('',(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v))&&this.Om((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).a)}if(a.Zj(aQe)||a.Zj(o$e)||a.Zj(L9e)){this.Om(psd(this));(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).c&&((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v==null||!(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v.length)&&!(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).J.length?cb(this._c(),W9e):kb(this._c(),W9e)}};var Lib=Zae(Z8e,'AbstractColorPickerConnector',812);tyb(220,6,{6:1,41:1,220:1,3:1},JYd);_.a=null;_.b=false;_.c=false;var bpb=Zae(u5e,'ColorPickerState',220);YGe(ps)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")