$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback9("function sMc(){}\nfunction uMc(){}\nfunction wMc(){}\nfunction vsd(){a0b.call(this)}\nfunction b3b(a,b){return OL(a.G.Bo(b))}\nfunction d_d(){E_b.call(this);this.I=YOe;this.a=new Lce}\nfunction qWc(c,a){var b=c;a.notifyChildrenOfSizeChange=bje(function(){b.cl()})}\nfunction yWc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction nWc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction mWc(a,b){var c,d;for(c=t8d(new u8d(a.f));c.a.Ug();){d=OL(A8d(c));if(VL(a.f.Bo(d))===VL(b)){return d}}return null}\nfunction rWc(a,b){var c,d;d=mWc(a,b);d!=null&&a.f.Eo(d);c=ML(a.a.Bo(b),534);if(c){a.a.Eo(b);return ed(a,c)}else if(b){return ed(a,b)}return false}\nfunction oWc(a){var b,c,d;d=(Svb(),a._b).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];Qvb.Ag(c,ape)}}\nfunction sWc(a,b){var c,d,e;if((du(),b).hasAttribute(DKe)){e=iu(b,DKe);a.e.Do(e,b);Ot(b,'')}else{d=(Svb(),$xb(b));for(c=0;c<d;c++){sWc(a,Zxb(b,c))}}}\nfunction tWc(a,b,c){var d,e;if(!b){return}d=NL(a.e.Bo(c));if(!d&&a.d){throw new M4d('No location '+c+' found')}e=ML(a.f.Bo(c),9);if(e==b){return}!!e&&rWc(a,e);a.d||(d=(Svb(),a._b));Wc(a,b,(Svb(),d));a.f.Do(c,b)}\nfunction uWc(a,b){var c,d,e;d=b.Pd();if(d.$b!=a){return}e=ML(a.a.Bo(d),534);if(vbc(b.Nd())){if(!e){c=mWc(a,d);ed(a,d);e=new Dbc(b,a.b);Vc(a,e,NL(a.e.Bo(c)));a.a.Do(d,e)}qbc(e.a)}else{if(e){c=mWc(a,d);ed(a,e);Vc(a,d,NL(a.e.Bo(c)));a.a.Eo(d)}}}\nfunction oMc(c){var d={setter:function(a,b){a.a=b},getter:function(a){return a.a}};c.uk(hnb,UOe,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.uk(hnb,VOe,d);var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.uk(hnb,WOe,d)}\nfunction vWc(){var a;fd.call(this);this.e=new Lce;this.f=new Lce;this.a=new Lce;ob(this,(Svb(),Uv($doc)));a=this._b.style;Qx(a,zye,(Wx(),nje));Qx(a,OAe,(yB(),Ske));Qx(a,$Ae,Ske);(z5b(),!y5b&&(y5b=new R5b),z5b(),y5b).a.i&&Qx(a,Eje,(DA(),gke));Mt(this._b,YOe);Kb(this._b,lJe,true)}\nfunction pWc(a,b,c){var d;b=lWc(a,b);d=Bdc(c+'/layouts/');b=T5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',XOe+d+'$3\"');b=T5d(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',XOe+d+'$3\"');b=T5d(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Ot((Svb(),a._b),b);a.e.Ic();sWc(a,a._b);oWc(a);a.c=Zvb(a._b);!a.c&&(a.c=a._b);qWc(a,a.c);a.d=true}\nfunction usd(a){var b,c,d;if(a.a){return}c=(!a.L&&(a.L=ug(a)),ML(ML(ML(a.L,6),114),395)).c;b=(!a.L&&(a.L=ug(a)),ML(ML(ML(a.L,6),114),395)).b;c!=null&&(b=b3b(a.D,'layouts/'+c+'.html'));if(b!=null){pWc((!a.B&&(a.B=ah(a)),ML(a.B,254)),b,c3b(a.D))}else{d=c!=null?'Layout file layouts/'+c+'.html is missing.':'Layout file not specified.';Ot(eb((!a.B&&(a.B=ah(a)),ML(a.B,254))),'<em>'+d+' Components will be drawn for debug purposes.<\\/em>')}a.a=true}\nfunction lWc(a,b){var c,d,e,f,g,h,j,k;b=T5d(b,'_UID_',a.g+'__');a.i='';d=0;f=b.toLowerCase();h='';j=f.indexOf('<script',0);while(j>0){h+=b.substr(d,j-d);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.i+=b.substr(j+1,e-(j+1))+';';g=d=e+9;j=f.indexOf('<script',g)}h+=c6d(b,d,b.length-d);f=h.toLowerCase();k=f.indexOf('<body');if(k<0){h=h}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(h=h.substr(k,c-k)):(h=c6d(h,k,h.length-k))}return h}\nvar UOe='childLocations',VOe='templateContents',WOe='templateName',XOe='<$1 $2src=\"',YOe='v-customlayout';bsb(1724,1,lAe);_.Ie=function rMc(){fNc(this.b,hnb,ymb);XMc(this.b,rEe,pgb);ZMc(this.b,sbb,IEe,new sMc);ZMc(this.b,pgb,IEe,new uMc);ZMc(this.b,hnb,IEe,new wMc);dNc(this.b,pgb,wke,new PMc(sbb));dNc(this.b,pgb,tke,new PMc(hnb));oMc(this.b);bNc(this.b,hnb,UOe,new QMc(hGe,FL(BL(P9,1),JEe,4,0,[new PMc(amb),new PMc(zpb)])));bNc(this.b,hnb,VOe,new PMc(zpb));bNc(this.b,hnb,WOe,new PMc(zpb));Nsc((!Gsc&&(Gsc=new Tsc),Gsc),this.a.d)};bsb(1726,1,$He,sMc);_.ok=function tMc(a,b){return new vWc};var B9=_3d(QCe,'ConnectorBundleLoaderImpl/9/1/1',1726);bsb(1727,1,$He,uMc);_.ok=function vMc(a,b){return new vsd};var C9=_3d(QCe,'ConnectorBundleLoaderImpl/9/1/2',1727);bsb(1728,1,$He,wMc);_.ok=function xMc(a,b){return new d_d};var D9=_3d(QCe,'ConnectorBundleLoaderImpl/9/1/3',1728);bsb(254,208,{14:1,11:1,13:1,12:1,25:1,30:1,15:1,28:1,10:1,9:1,254:1,19:1},vWc);_.Hc=function wWc(a){throw new G6d};_.Ic=function xWc(){Qc(this);this.f.Ic();this.a.Ic()};_.cl=function zWc(){};_.sc=function AWc(a){Xb(this,a);Svb();if(Mxb((du(),a).type)==ape){gbc(this,true);Lxb(a,true)}};_.tc=function BWc(){Yb(this);!!this.c&&(this.c.notifyChildrenOfSizeChange=null,undefined)};_.Jc=function CWc(a){return rWc(this,a)};_.jc=function DWc(a){Mt((Svb(),this._b),a);Kb(this._b,lJe,true)};_.d=false;_.i='';var sbb=_3d(vke,'VCustomLayout',254);bsb(1725,514,{7:1,16:1,122:1,92:1,133:1,26:1,35:1,34:1,32:1,153:1,261:1,33:1,3:1},vsd);_.Nd=function wsd(){return !this.L&&(this.L=ug(this)),ML(ML(ML(this.L,6),114),395)};_.zd=function xsd(){return !this.L&&(this.L=ug(this)),ML(ML(ML(this.L,6),114),395)};_.Ai=function ysd(){return !this.L&&(this.L=ug(this)),ML(ML(ML(this.L,6),114),395)};_.Pd=function zsd(){return !this.B&&(this.B=ah(this)),ML(this.B,254)};_.Bd=function Asd(){(!this.B&&(this.B=ah(this)),ML(this.B,254)).b=this.D;(!this.B&&(this.B=ah(this)),ML(this.B,254)).g=this.G};_.Hi=function Bsd(){nWc((!this.B&&(this.B=ah(this)),ML(this.B,254),Zvb(eb((!this.B&&(this.B=ah(this)),ML(this.B,254))))))};_.ke=function Csd(b){var c,d,e,f,g,h;usd(this);for(d=bi(this).Pc();d.Ug();){c=ML(d.Vg(),16);e=OL((!this.L&&(this.L=ug(this)),ML(ML(ML(this.L,6),114),395)).a.Bo(c));try{tWc((!this.B&&(this.B=ah(this)),ML(this.B,254)),c.Pd(),e)}catch(a){a=$rb(a);if(QL(a,37)){wie(yie((V3d(pgb),pgb.k)),\"Child not rendered as no slot with id '\"+e+\"' has been defined\")}else throw Zrb(a)}}for(g=b.a.Pc();g.Ug();){f=ML(g.Vg(),16);if(f.xd()==this){continue}h=f.Pd();h.qc()&&rWc((!this.B&&(this.B=ah(this)),ML(this.B,254)),h)}};_.Dd=function Dsd(a){eh(this,a);usd(this);yWc((!this.B&&(this.B=ah(this)),ML(this.B,254)).i);(!this.B&&(this.B=ah(this)),ML(this.B,254)).i=null};_.le=function Esd(a){uWc((!this.B&&(this.B=ah(this)),ML(this.B,254)),a)};_.li=function Fsd(a,b){};_.a=false;var pgb=_3d('com.vaadin.client.ui.customlayout',GIe,1725);bsb(395,114,{6:1,42:1,114:1,395:1,3:1},d_d);var hnb=_3d('com.vaadin.shared.ui.customlayout','CustomLayoutState',395);bje($q)(9);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-9.js\n")
