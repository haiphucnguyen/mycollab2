$wnd.com_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback9("function yVc(){}\nfunction AVc(){}\nfunction CVc(){}\nfunction UBd(){J4b.call(this)}\nfunction C7b(a,b){return PC(a.q.Zo(b))}\nfunction ebe(){l4b.call(this);this.I=k2e;this.a=new qpe}\nfunction S3c(c,a){var b=c;a.notifyChildrenOfSizeChange=Twe(function(){b.zl()})}\nfunction $3c(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction P3c(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction O3c(a,b){var c,d;for(c=$ke(new _ke(a.f));c.a.If();){d=PC(fle(c));if(WC(a.f.Zo(d))===WC(b)){return d}}return null}\nfunction T3c(a,b){var c,d;d=O3c(a,b);d!=null&&a.f.ap(d);c=NC(a.a.Zo(b),551);if(c){a.a.ap(b);return Pub(a,c)}else if(b){return Pub(a,b)}return false}\nfunction Q3c(a){var b,c,d;d=(dqb(),a.bc).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];bqb.Ee(c,tBe)}}\nfunction U3c(a,b){var c,d,e;if((Zk(),b).hasAttribute(iZe)){e=cl(b,iZe);a.e._o(e,b);Ik(b,'')}else{d=(dqb(),lsb(b));for(c=0;c<d;c++){U3c(a,ksb(b,c))}}}\nfunction V3c(a,b,c){var d,e;if(!b){return}d=OC(a.e.Zo(c));if(!d&&a.d){throw new phe('No location '+c+' found')}e=NC(a.f.Zo(c),9);if(e==b){return}!!e&&T3c(a,e);a.d||(d=(dqb(),a.bc));Fub(a,b,(dqb(),d));a.f._o(c,b)}\nfunction W3c(a,b){var c,d,e;d=b.Og();if(d.ac!=a){return}e=NC(a.a.Zo(d),551);if(hfc(b.Mg())){if(!e){c=O3c(a,d);Pub(a,d);e=new pfc(b,a.b);Eub(a,e,OC(a.e.Zo(c)));a.a._o(d,e)}cfc(e.a)}else{if(e){c=O3c(a,d);Pub(a,e);Eub(a,d,OC(a.e.Zo(c)));a.a.ap(d)}}}\nfunction uVc(c){var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.Rk(zgb,g2e,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.Rk(zgb,h2e,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.Rk(zgb,i2e,d)}\nfunction X3c(){var a;Qub.call(this);this.e=new qpe;this.f=new qpe;this.a=new qpe;Atb(this,(dqb(),Om($doc)));a=this.bc.style;To(a,xMe,(Zo(),Mye));To(a,rOe,(Bs(),JCe));To(a,GOe,JCe);(Y8b(),!X8b&&(X8b=new o9b),Y8b(),X8b).a.i&&To(a,jye,(Gr(),UCe));Gk(this.bc,k2e);Wtb(this.bc,SXe,true)}\nfunction R3c(a,b,c){var d;b=N3c(a,b);d=shc(c+'/layouts/');b=xie(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',j2e+d+'$3\"');b=xie(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',j2e+d+'$3\"');b=xie(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Ik((dqb(),a.bc),b);a.e.ff();U3c(a,a.bc);Q3c(a);a.c=kqb(a.bc);!a.c&&(a.c=a.bc);S3c(a,a.c);a.d=true}\nfunction N3c(a,b){var c,d,e,f,g,h,j,k;b=xie(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';j=f.indexOf('<script',0);while(j>0){h+=b.substr(d,j-d);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.i+=b.substr(j+1,e-(j+1))+';';g=d=e+9;j=f.indexOf('<script',g)}h+=Iie(b,d,b.length-d);f=h.toLowerCase();k=f.indexOf('<body');if(k<0){h=h}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(h=h.substr(k,c-k)):(h=Iie(h,k,h.length-k))}return h}\nfunction TBd(a){var b,c,d;if(a.a){return}c=(!a.U&&(a.U=jJb(a)),NC(NC(NC(a.U,6),118),401)).c;b=(!a.U&&(a.U=jJb(a)),NC(NC(NC(a.U,6),118),401)).b;c!=null&&(b=C7b(a.N,'layouts/'+c+'.html'));if(b!=null){R3c((!a.L&&(a.L=TJb(a)),NC(a.L,267)),b,D7b(a.N))}else{d=c!=null?'Layout file layouts/'+c+'.html is missing.':'Layout file not specified.';Ik(qtb((!a.L&&(a.L=TJb(a)),NC(a.L,267))),'<em>'+d+' Components will be drawn for debug purposes.<\\/em>')}a.a=true}\nvar g2e='childLocations',h2e='templateContents',i2e='templateName',j2e='<$1 $2src=\"',k2e='v-customlayout';$lb(1812,1,kOe);_.vc=function xVc(){mWc(this.b,zgb,Pfb);cWc(this.b,ySe,_8);eWc(this.b,g4,PSe,new yVc);eWc(this.b,_8,PSe,new AVc);eWc(this.b,zgb,PSe,new CVc);kWc(this.b,_8,HDe,new WVc(g4));kWc(this.b,_8,EDe,new WVc(zgb));uVc(this.b);iWc(this.b,zgb,g2e,new XVc(xUe,GC(CC(w2,1),QSe,4,0,[new WVc(qfb),new WVc(ejb)])));iWc(this.b,zgb,h2e,new WVc(ejb));iWc(this.b,zgb,i2e,new WVc(ejb));aWc(this.b,_8,new MVc(M2,VSe,GC(CC(ejb,1),dxe,2,4,[WVe])));Kzc((!Dzc&&(Dzc=new Qzc),Dzc),this.a.d)};$lb(1814,1,EWe,yVc);_.Jk=function zVc(a,b){return new X3c};var i2=Ege(PQe,'ConnectorBundleLoaderImpl/9/1/1',1814);$lb(1815,1,EWe,AVc);_.Jk=function BVc(a,b){return new UBd};var j2=Ege(PQe,'ConnectorBundleLoaderImpl/9/1/2',1815);$lb(1816,1,EWe,CVc);_.Jk=function DVc(a,b){return new ebe};var k2=Ege(PQe,'ConnectorBundleLoaderImpl/9/1/3',1816);$lb(267,212,{14:1,12:1,11:1,13:1,26:1,33:1,15:1,28:1,10:1,9:1,267:1,20:1},X3c);_.ef=function Y3c(a){throw new kje};_.ff=function Z3c(){zub(this);this.f.ff();this.a.ff()};_.zl=function _3c(){};_.Ae=function a4c(a){hub(this,a);dqb();if(Zrb((Zk(),a).type)==tBe){Rec(this,true);Yrb(a,true)}};_._e=function b4c(){iub(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.gf=function c4c(a){return T3c(this,a)};_.Te=function d4c(a){Gk((dqb(),this.bc),a);Wtb(this.bc,SXe,true)};_.d=false;_.i='';var g4=Ege(GDe,'VCustomLayout',267);$lb(1813,265,{7:1,16:1,120:1,93:1,136:1,24:1,29:1,34:1,30:1,265:1,152:1,259:1,31:1,3:1},UBd);_.Mg=function VBd(){return !this.U&&(this.U=jJb(this)),NC(NC(NC(this.U,6),118),401)};_.yg=function WBd(){return !this.U&&(this.U=jJb(this)),NC(NC(NC(this.U,6),118),401)};_.Ii=function XBd(){return !this.U&&(this.U=jJb(this)),NC(NC(NC(this.U,6),118),401)};_.Og=function YBd(){return !this.L&&(this.L=TJb(this)),NC(this.L,267)};_.Ag=function ZBd(){(!this.L&&(this.L=TJb(this)),NC(this.L,267)).b=this.N;(!this.L&&(this.L=TJb(this)),NC(this.L,267)).g=this.P};_.Pi=function $Bd(){P3c((!this.L&&(this.L=TJb(this)),NC(this.L,267),kqb(qtb((!this.L&&(this.L=TJb(this)),NC(this.L,267))))))};_.gh=function _Bd(b){var c,d,e,f,g,h;TBd(this);for(d=DKb(this).nf();d.If();){c=NC(d.Jf(),16);e=PC((!this.U&&(this.U=jJb(this)),NC(NC(NC(this.U,6),118),401)).a.Zo(c));try{V3c((!this.L&&(this.L=TJb(this)),NC(this.L,267)),c.Og(),e)}catch(a){a=Xlb(a);if(RC(a,38)){ave(cve((xge(_8),_8.k)),\"Child not rendered as no slot with id '\"+e+\"' has been defined\")}else throw Wlb(a)}}for(g=b.a.nf();g.If();){f=NC(g.Jf(),16);if(f.wg()==this){continue}h=f.Og();h.Ze()&&T3c((!this.L&&(this.L=TJb(this)),NC(this.L,267)),h)}};_.Cg=function aCd(a){WJb(this,a);TBd(this);$3c((!this.L&&(this.L=TJb(this)),NC(this.L,267)).i);(!this.L&&(this.L=TJb(this)),NC(this.L,267)).i=null};_.hh=function bCd(a){W3c((!this.L&&(this.L=TJb(this)),NC(this.L,267)),a)};_.ti=function cCd(a,b){};_.a=false;var _8=Ege('com.vaadin.client.ui.customlayout',lXe,1813);$lb(401,118,{6:1,40:1,118:1,401:1,3:1},ebe);var zgb=Ege('com.vaadin.shared.ui.customlayout','CustomLayoutState',401);Twe(Uh)(9);\n//# sourceURL=com.mycollab.widgetset.MyCollabMobileWidgetSet-9.js\n")
