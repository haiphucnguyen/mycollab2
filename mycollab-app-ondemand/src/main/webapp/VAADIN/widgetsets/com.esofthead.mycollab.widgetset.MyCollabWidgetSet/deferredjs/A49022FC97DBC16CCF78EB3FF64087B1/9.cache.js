$wnd.com_esofthead_mycollab_widgetset_MyCollabWidgetSet.runAsyncCallback9("function XIb(a){return a.g}\nfunction ZIb(a,b){dIb(a,b);--a.i}\nfunction ksd(){ue.call(this)}\nfunction CYd(){lf.call(this);this.G=H9e}\nfunction fy(a){return (uv(),tv).Re(a,'col')}\nfunction ECc(a,b){zfc(a.a,new XMc(new lNc(_ob),'openPopup'),XO(TO(ysb,1),bIe,1,3,[(cae(),b?bae:aae)]))}\nfunction YIb(a,b){if(b<0){throw new Y9d('Cannot access a row with a negative index: '+b)}if(b>=a.i){throw new Y9d(wNe+b+xNe+a.i)}}\nfunction _Ib(a,b){if(a.i==b){return}if(b<0){throw new Y9d('Cannot set number of rows to '+b)}if(a.i<b){bJb((oCb(),a.G),b-a.i,a.g);a.i=b}else{while(a.i>b){ZIb(a,a.i-1)}}}\nfunction aJb(a,b){NHb();kIb.call(this);eIb(this,new GIb(this));hIb(this,new QJb(this));fIb(this,new EJb(this));$Ib(this,b);_Ib(this,a)}\nfunction DJb(a,b,c){var d,e;b=b>1?b:1;e=a.a.childNodes.length;if(e<b){for(d=e;d<b;d++){ju(a.a,fy($doc))}}else if(!c&&e>b){for(d=e;d>b;d--){su(a.a,a.a.lastChild)}}}\nfunction bJb(a,b,c){var d=$doc.createElement('td');d.innerHTML=NRe;var e=$doc.createElement('tr');for(var f=0;f<c;f++){var g=d.cloneNode(true);e.appendChild(g)}a.appendChild(e);for(var h=1;h<b;h++){a.appendChild(e.cloneNode(true))}}\nfunction jsd(a){if((!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).c&&((!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v==null||Ice('',(!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v))){return (!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).a}return (!a.U&&(a.U=Ed(a)),cP(cP(a.U,6),220)).v}\nfunction $Ib(a,b){var c,d,e,f;if(a.g==b){return}if(b<0){throw new Y9d('Cannot set number of columns to '+b)}if(a.g>b){for(c=0;c<a.i;c++){for(d=a.g-1;d>=b;d--){PHb(a,c,d);e=RHb(a,c,d,false);f=MJb(a.G,c);f.removeChild(e)}}}else{for(c=0;c<a.i;c++){for(d=a.g;d<b;d++){_Hb(a,c,d)}}}a.g=b;DJb(a.I,b,false)}\nvar R8e={53:1,7:1,16:1,29:1,34:1,33:1,35:1,32:1,3:1},S8e='com.vaadin.client.ui.colorpicker',T8e='background',E9e='showDefaultCaption',F9e='setOpen',G9e='setColor',H9e='v-colorpicker',P9e='v-default-caption-width';syb(1,null,{});_.gC=function W(){return this.cZ};syb(121,9,CIe);_.qe=function Yk(a){return Rb(this,a,(CF(),CF(),BF))};syb(820,28,yNe);_.qe=function lIb(a){return Rb(this,a,(CF(),CF(),BF))};syb(245,48,ANe);_.qe=function TIb(a){return Rb(this,a,(CF(),CF(),BF))};syb(652,820,yNe,aJb);_.mh=function cJb(){var a;a=(oCb(),Hy($doc));cv(a,NRe);return a};_.nh=function dJb(a){return XIb(this)};_.oh=function eJb(){return this.i};_.ph=function fJb(a,b){YIb(this,a);if(b<0){throw new Y9d('Cannot access a column with a negative index: '+b)}if(b>=this.g){throw new Y9d(uNe+b+vNe+this.g)}};_.qh=function gJb(a){YIb(this,a)};_.g=0;_.i=0;var mX=Sae(jHe,'Grid',652);syb(52,598,BNe);_.qe=function mJb(a){return Rb(this,a,(CF(),CF(),BF))};syb(381,9,MNe);_.qe=function nKb(a){return Sb(this,a,(CF(),CF(),BF))};syb(812,33,R8e);_.Wc=function lsd(){return false};_.Zc=function msd(){return !this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)};_.Lc=function nsd(){return !this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)};_.Nc=function osd(){gP(this._c(),54)&&cP(this._c(),54).qe(this)};_.Pc=function psd(a){ne(this,a);if(a.Zj(_Pe)){this.Nm();(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).c&&((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v==null||Ice('',(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v))&&this.Om((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).a)}if(a.Zj(VPe)||a.Zj(wYe)||a.Zj(E9e)){this.Om(jsd(this));(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).c&&((!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v==null||!(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).v.length)&&!(!this.U&&(this.U=Ed(this)),cP(cP(this.U,6),220)).J.length?cb(this._c(),P9e):kb(this._c(),P9e)}};var Kib=Sae(S8e,'AbstractColorPickerConnector',812);syb(220,6,{6:1,41:1,220:1,3:1},CYd);_.a=null;_.b=false;_.c=false;var apb=Sae(n5e,'ColorPickerState',220);RGe(ps)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabWidgetSet-9.js\n")
