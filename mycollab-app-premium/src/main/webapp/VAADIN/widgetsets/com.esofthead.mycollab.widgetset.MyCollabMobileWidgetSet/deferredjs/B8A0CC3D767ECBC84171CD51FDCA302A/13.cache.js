$wnd.com_esofthead_mycollab_widgetset_MyCollabMobileWidgetSet.runAsyncCallback13("function vvc(){}\nfunction xvc(){}\nfunction zvc(){}\nfunction qed(){HXb.call(this)}\nfunction L$b(a,b){return IM(a.H.Jm(b),2)}\nfunction M$b(a){return a.e.q+'themes/'+a.Q.b}\nfunction Wud(){jXb.call(this);this.I=b2d;this.b=new HFd}\nfunction ZIc(c,a){var b=c;a.notifyChildrenOfSizeChange=IId(function(){b.Ok()})}\nfunction fJc(b){try{b!=null&&eval('{ var document = $doc; var window = $wnd; '+b+'}')}catch(a){}}\nfunction WIc(b){if(b&&b.iLayoutJS){try{b.iLayoutJS();return true}catch(a){return false}}else{return false}}\nfunction VIc(a,b){var c,d;for(c=ECd($Ad(a.g));c.b.ih();){d=IM(JCd(c),2);if(OM(a.g.Jm(d))===OM(b)){return d}}return null}\nfunction $Ic(a,b){var c,d;d=VIc(a,b);d!=null&&a.g.Mm(d);c=IM(a.b.Jm(b),250);if(c){a.b.Mm(b);return vc(a,c)}else if(b){return vc(a,b)}return false}\nfunction XIc(a){var b,c,d;d=(Crb(),a.ac).getElementsByTagName('IMG');for(b=0;b<d.length;b++){c=d[b];Arb.Jg(c,vNd)}}\nfunction _Ic(a,b){var c,d,e;e=dt((Zs(),b),UZd);if(Szd('',e)){d=(Crb(),Ntb(b));for(c=0;c<d;c++){_Ic(a,Mtb(b,c))}}else{a.f.Lm(e,b);Ks(b,'')}}\nfunction aJc(a,b,c){var d,e;if(!b){return}d=JM(a.f.Jm(c));if(!d&&a.e){throw new Ryd('No location '+c+HRd)}e=IM(a.g.Jm(c),18);if(e==b){return}!!e&&$Ic(a,e);a.e||(d=(Crb(),a.ac));lc(a,b,(Crb(),d));a.g.Lm(c,b)}\nfunction bJc(a,b){var c,d,e;d=b.Wc();e=IM(a.b.Jm(d),250);if(K7b(b.Id())){if(!e){c=VIc(a,d);vc(a,d);e=new Q7b(b,a.c);kc(a,e,JM(a.f.Jm(c)));a.b.Lm(d,e)}F7b(e.b)}else{if(e){c=VIc(a,d);vc(a,e);kc(a,d,JM(a.f.Jm(c)));a.b.Mm(d)}}}\nfunction rvc(c){var d={setter:function(a,b){a.c=b},getter:function(a){return a.c}};c.hk(tib,Z1d,d);var d={setter:function(a,b){a.b=b},getter:function(a){return a.b}};c.hk(tib,$1d,d);var d={setter:function(a,b){a.d=b},getter:function(a){return a.d}};c.hk(tib,_1d,d)}\nfunction cJc(){var a;wc.call(this);this.f=new HFd;this.g=new HFd;this.b=new HFd;this.j='';this.e=false;ib(this,(Crb(),Qu($doc)));a=this.ac.style;Mw(a,IPd,(Tw(),TId));a[ERd]=0+(DA(),dJd);a[$Rd]=hKd;(e1b(),!d1b&&(d1b=new v1b),e1b(),d1b).b.i&&Mw(a,bJd,(Fz(),zJd));Is(this.ac,b2d);Eb(this.ac,EYd,true)}\nfunction ped(a){var b,c;if(IM(mg(a),80).e){return}c=(!a.G&&(a.G=xf(a)),IM(IM(IM(a.G,5),37),151)).d;b=(!a.G&&(a.G=xf(a)),IM(IM(IM(a.G,5),37),151)).c;if(c!=null){b=L$b(a.v,'layouts/'+c+'.html');b==null&&(b='<em>Layout file layouts/'+c+'.html is missing. Components will be drawn for debug purposes.<\\/em>')}YIc(IM(mg(a),80),b,M$b(a.v))}\nfunction UIc(a,b){var c,d,e,f,g,i,j,k;b=_zd(b,'_UID_',a.i+'__');a.j='';d=0;f=b.toLowerCase();i='';j=f.indexOf('<script',0);while(j>0){i+=eAd(b,d,j);j=f.indexOf('>',j);e=f.indexOf('<\\/script>',j);a.j+=eAd(b,j+1,e)+';';g=d=e+9;j=f.indexOf('<script',g)}i+=dAd(b,d);f=i.toLowerCase();k=f.indexOf('<body');if(k<0){i=i}else{k=f.indexOf('>',k)+1;c=f.indexOf('<\\/body>',k);c>k?(i=eAd(i,k,c)):(i=dAd(i,k))}return i}\nfunction YIc(a,b,c){var d;b=UIc(a,b);d=c+'/layouts/';b=_zd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=\"((?![a-z]+:)[^/][^\"]+)\"',a2d+d+'$3\"');b=_zd(b,'<((?:img)|(?:IMG))\\\\s([^>]*)src=[^\"]((?![a-z]+:)[^/][^ />]+)[ />]',a2d+d+'$3\"');b=_zd(b,'(<[^>]+style=\"[^\"]*url\\\\()((?![a-z]+:)[^/][^\"]+)(\\\\)[^>]*>)','$1 '+d+'$2 $3');Ks((Crb(),a.ac),b);a.f.zc();_Ic(a,a.ac);XIc(a);a.d=Jrb(a.ac);!a.d&&(a.d=a.ac);ZIc(a,a.d);a.e=true}\nvar Z1d='templateContents',$1d='childLocations',_1d='templateName',a2d='<$1 $2src=\"',b2d='v-customlayout';Anb(717,1,aRd);_.ze=function uvc(){EAc(this.c,tib,Khb);uAc(this.c,FUd,Jeb);xAc(this.c,tib,SUd,new vvc);xAc(this.c,P9,SUd,new xvc);xAc(this.c,Jeb,SUd,new zvc);CAc(this.c,Jeb,JJd,new mAc(tib));CAc(this.c,Jeb,WJd,new mAc(P9));rvc(this.c);AAc(this.c,tib,Z1d,new mAc(pkb));AAc(this.c,tib,$1d,new nAc(dWd,xM(vmb,gVd,8,0,[new mAc(shb),new mAc(pkb)])));AAc(this.c,tib,_1d,new mAc(pkb));zjc((!sjc&&(sjc=new Fjc),sjc),this.b.e)};Anb(553,1,yXd,vvc);_.bk=function wvc(a,b){return new Wud};Anb(1394,1,yXd,xvc);_.bk=function yvc(a,b){return new cJc};Anb(1145,1,yXd,zvc);_.bk=function Avc(a,b){return new qed};Anb(80,568,{2381:1,2349:1,1934:1,2319:1,118:1,2359:1,2267:1,300:1,283:1,18:1,80:1,215:1},cJc);_.yc=function dJc(a){throw new WAd};_.zc=function eJc(){fc(this);this.g.zc();this.b.zc()};_.Ok=function gJc(){};_.tc=function hJc(a){Rb(this,a);if((Crb(),utb(a))==vNd){a7b(this,true);ttb(a,true)}};_.uc=function iJc(){Sb(this);!!this.d&&(this.d.notifyChildrenOfSizeChange=null,undefined)};_.Ac=function jJc(a){return $Ic(this,a)};_.kc=function kJc(a){Is((Crb(),this.ac),a);Eb(this.ac,EYd,true)};_.e=false;Anb(1961,2037,{412:1,7:1,1473:1,74:1,397:1,66:1,559:1,163:1,213:1,77:1,408:1,127:1,3:1},qed);_.Id=function red(){return !this.G&&(this.G=xf(this)),IM(IM(IM(this.G,5),37),151)};_.rd=function sed(){return !this.G&&(this.G=xf(this)),IM(IM(IM(this.G,5),37),151)};_.Ei=function ted(){return !this.G&&(this.G=xf(this)),IM(IM(IM(this.G,5),37),151)};_.Wc=function ued(){return IM(mg(this),80)};_.td=function ved(){IM(mg(this),80).c=this.v;IM(mg(this),80).i=this.A};_.Pi=function wed(){WIc((IM(mg(this),80),Jrb(Y(IM(mg(this),80)))))};_.ae=function xed(b){var c,d,e,f,g,i;ped(this);for(d=Tg(this).Gc();d.ih();){c=IM(d.jh(),7);e=IM((!this.G&&(this.G=xf(this)),IM(IM(IM(this.G,5),37),151)).b.Jm(c),2);try{aJc(IM(mg(this),80),c.Wc(),e)}catch(a){a=xnb(a);if(!KM(a,301))throw wnb(a)}}for(g=b.b.Gc();g.ih();){f=IM(g.jh(),7);if(f.pd()==this){continue}i=f.Wc();i.rc()&&$Ic(IM(mg(this),80),i)}};_.vd=function yed(a){og(this,a);ped(this);fJc(IM(mg(this),80).j);IM(mg(this),80).j=null};_.be=function zed(a){bJc(IM(mg(this),80),a)};_.pi=function Aed(a,b){};Anb(151,37,{5:1,376:1,37:1,151:1,3:1},Wud);var tib=pyd('com.vaadin.shared.ui.customlayout.','CustomLayoutState',151),Jeb=pyd('com.vaadin.client.ui.customlayout.',JXd,1961),P9=pyd(K$d,'VCustomLayout',80),j6=pyd(w_d,'ConnectorBundleLoaderImpl$13$1$1',553),k6=pyd(w_d,'ConnectorBundleLoaderImpl$13$1$2',1394),l6=pyd(w_d,'ConnectorBundleLoaderImpl$13$1$3',1145);IId(Np)(13);\n//# sourceURL=com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet-13.js\n")
