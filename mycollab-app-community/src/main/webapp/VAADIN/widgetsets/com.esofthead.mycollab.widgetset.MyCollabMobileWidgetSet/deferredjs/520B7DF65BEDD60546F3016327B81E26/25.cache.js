$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback25("function RKc(){}\nfunction TKc(){}\nfunction VKc(){}\nfunction psd(){a0b.call(this)}\nfunction b3b(a,b){return ML(a.G.Eo(b))}\nfunction P$d(){E_b.call(this);this.I=uQe;this.a=new Jce}\nfunction uWc(c,a){var b=c;a.notifyChildrenOfSizeChange=_ie(function(){b._k()})}\nfunction CWc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction rWc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction qWc(a,b){var c,d;for(c=r8d(new s8d(a.f));c.a.Ug();){d=ML(y8d(c));if(TL(a.f.Eo(d))===TL(b)){return d}}return null}\nfunction vWc(a,b){var c,d;d=qWc(a,b);d!=null&&a.f.Ho(d);c=KL(a.a.Eo(b),531);if(c){a.a.Ho(b);return ed(a,c)}else if(b){return ed(a,b)}return false}\nfunction sWc(a){var b,c,d;d=(Uvb(),a._b).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];Svb.Ag(c,_oe)}}\nfunction wWc(a,b){var c,d,e;if((bu(),b).hasAttribute(DKe)){e=gu(b,DKe);a.e.Go(e,b);Mt(b,'')}else{d=(Uvb(),ayb(b));for(c=0;c<d;c++){wWc(a,_xb(b,c))}}}\nfunction xWc(a,b,c){var d,e;if(!b){return}d=LL(a.e.Eo(c));if(!d&&a.d){throw new K4d('No location '+c+' found')}e=KL(a.f.Eo(c),9);if(e==b){return}!!e&&vWc(a,e);a.d||(d=(Uvb(),a._b));Wc(a,b,(Uvb(),d));a.f.Go(c,b)}\nfunction yWc(a,b){var c,d,e;d=b.Pd();if(d.$b!=a){return}e=KL(a.a.Eo(d),531);if(sbc(b.Nd())){if(!e){c=qWc(a,d);ed(a,d);e=new Abc(b,a.b);Vc(a,e,LL(a.e.Eo(c)));a.a.Go(d,e)}nbc(e.a)}else{if(e){c=qWc(a,d);ed(a,e);Vc(a,d,LL(a.e.Eo(c)));a.a.Ho(d)}}}\nfunction NKc(c){var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.tk(hnb,qQe,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.tk(hnb,rQe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.tk(hnb,sQe,d)}\nfunction zWc(){var a;fd.call(this);this.e=new Jce;this.f=new Jce;this.a=new Jce;ob(this,(Uvb(),Sv($doc)));a=this._b.style;Ox(a,yye,(Ux(),lje));Ox(a,OAe,(wB(),Rke));Ox(a,aBe,Rke);(C5b(),!B5b&&(B5b=new T5b),C5b(),B5b).a.g&&Ox(a,Cje,(BA(),eke));Kt(this._b,uQe);Kb(this._b,nJe,true)}\nfunction osd(a){var b,c,d;if(a.a){return}c=(!a.L&&(a.L=ug(a)),KL(KL(KL(a.L,6),114),390)).c;b=(!a.L&&(a.L=ug(a)),KL(KL(KL(a.L,6),114),390)).b;c!=null&&(b=b3b(a.D,'layouts/'+c+'.html'));if(b!=null){tWc(KL(ah(a),249),b,c3b(a.D))}else{d=c!=null?'Layout file layouts/'+c+'.html is missing.':'Layout file not specified.';Mt(eb(KL(ah(a),249)),'<em>'+d+' Components will be drawn for debug purposes.<\\/em>')}a.a=true}\nfunction tWc(a,b,c){var d;b=pWc(a,b);d=xdc(c+'/layouts/');b=R5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',tQe+d+'$3\"');b=R5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',tQe+d+'$3\"');b=R5d(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Mt((Uvb(),a._b),b);a.e.Ic();wWc(a,a._b);sWc(a);a.c=_vb(a._b);!a.c&&(a.c=a._b);uWc(a,a.c);a.d=true}\nfunction pWc(a,b){var c,d,e,f,g,h,j,k;b=R5d(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';j=f.indexOf('<script',0);while(j>0){h+=b.substr(d,j-d);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.i+=b.substr(j+1,e-(j+1))+';';g=d=e+9;j=f.indexOf('<script',g)}h+=a6d(b,d,b.length-d);f=h.toLowerCase();k=f.indexOf('<body');if(k<0){h=h}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(h=h.substr(k,c-k)):(h=a6d(h,k,h.length-k))}return h}\nvar qQe='childLocations',rQe='templateContents',sQe='templateName',tQe='<$1 $2src=\"',uQe='v-customlayout';dsb(1817,1,kAe);_.Ie=function QKc(){jNc(this.b,hnb,ymb);_Mc(this.b,GEe,sgb);bNc(this.b,ubb,HEe,new RKc);bNc(this.b,hnb,HEe,new TKc);bNc(this.b,sgb,HEe,new VKc);hNc(this.b,sgb,rke,new TMc(hnb));hNc(this.b,sgb,Cke,new TMc(ubb));NKc(this.b);fNc(this.b,hnb,qQe,new UMc(ZFe,DL(zL(R9,1),IEe,4,0,[new TMc(amb),new TMc(Bpb)])));fNc(this.b,hnb,rQe,new TMc(Bpb));fNc(this.b,hnb,sQe,new TMc(Bpb));Osc((!Hsc&&(Hsc=new Usc),Hsc),this.a.d)};dsb(1819,1,aIe,RKc);_.nk=function SKc(a,b){return new zWc};var T8=Z3d(PCe,'ConnectorBundleLoaderImpl/25/1/1',1819);dsb(1820,1,aIe,TKc);_.nk=function UKc(a,b){return new P$d};var U8=Z3d(PCe,'ConnectorBundleLoaderImpl/25/1/2',1820);dsb(1821,1,aIe,VKc);_.nk=function WKc(a,b){return new psd};var V8=Z3d(PCe,'ConnectorBundleLoaderImpl/25/1/3',1821);dsb(249,206,{14:1,11:1,13:1,12:1,25:1,30:1,15:1,28:1,10:1,9:1,249:1,19:1},zWc);_.Hc=function AWc(a){throw new E6d};_.Ic=function BWc(){Qc(this);this.f.Ic();this.a.Ic()};_._k=function DWc(){};_.sc=function EWc(a){Xb(this,a);Uvb();if(Oxb((bu(),a).type)==_oe){dbc(this,true);Nxb(a,true)}};_.tc=function FWc(){Yb(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function GWc(a){return vWc(this,a)};_.jc=function HWc(a){Kt((Uvb(),this._b),a);Kb(this._b,nJe,true)};_.d=false;_.i='';var ubb=Z3d(tke,'VCustomLayout',249);dsb(1818,509,{7:1,16:1,122:1,92:1,133:1,26:1,35:1,34:1,32:1,153:1,254:1,31:1,3:1},psd);_.Nd=function qsd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.zd=function rsd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.Ai=function ssd(){return !this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)};_.Pd=function tsd(){return KL(ah(this),249)};_.Bd=function usd(){KL(ah(this),249).b=this.D;KL(ah(this),249).g=this.G};_.Hi=function vsd(){rWc((KL(ah(this),249),_vb(eb(KL(ah(this),249)))))};_.ke=function wsd(b){var c,d,e,f,g,h;osd(this);for(d=ai(this).Pc();d.Ug();){c=KL(d.Vg(),16);e=ML((!this.L&&(this.L=ug(this)),KL(KL(KL(this.L,6),114),390)).a.Eo(c));try{xWc(KL(ah(this),249),c.Pd(),e)}catch(a){a=asb(a);if(OL(a,37)){uie(wie((T3d(sgb),sgb.k)),\"Child not rendered as no slot with id '\"+e+\"' has been defined\")}else throw _rb(a)}}for(g=b.a.Pc();g.Ug();){f=KL(g.Vg(),16);if(f.xd()==this){continue}h=f.Pd();h.qc()&&vWc(KL(ah(this),249),h)}};_.Dd=function xsd(a){dh(this,a);osd(this);CWc(KL(ah(this),249).i);KL(ah(this),249).i=null};_.le=function ysd(a){yWc(KL(ah(this),249),a)};_.li=function zsd(a,b){};_.a=false;var sgb=Z3d('com.vaadin.client.ui.customlayout',BIe,1818);dsb(390,114,{6:1,42:1,114:1,390:1,3:1},P$d);var hnb=Z3d('com.vaadin.shared.ui.customlayout','CustomLayoutState',390);_ie(Yq)(25);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-25.js\n")
