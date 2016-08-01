precision highp float;
precision highp int;
precision lowp sampler2D;
precision lowp samplerCube;
varying highp float v_pos_x;
varying highp vec2 v_TexCoordinateA;
varying highp vec2 v_TexCoordinateB;

uniform float v_masksize;
uniform sampler2D u_Texture;
uniform int modetype;
uniform float isright; 
 
void main() {
    highp vec4 colA = texture2D(u_Texture, v_TexCoordinateA);
    highp vec4 colB = texture2D(u_Texture, v_TexCoordinateB);
 
    colA.a = 1.0;
    colB.a = 1.0;
    float alpha = 0.0;
 	if(modetype==1){//全景模式
		
		float  xx = v_pos_x;
		float  masksize = v_masksize;
		float  Midsize = 0.0;
    
		if(xx < Midsize-masksize){
			alpha = 0.0;
		}else if( xx >Midsize+masksize){
			alpha = 1.0;
		}else{
			float  index =  xx - (Midsize - masksize);
			float al =  index * 1.0 / (masksize * 2.0);
			alpha = al;
		}
		gl_FragColor = colA * alpha + colB * (1.0 - alpha);
		
 	}else if(modetype==2){//平面模式
		float  xx = v_pos_x;
		if((xx <= 0.5 - v_masksize )&& (xx>=-0.5+v_masksize)){
			alpha = 0.0;
		}else if((xx >= 0.5 + v_masksize )|| (xx<=-0.5-v_masksize)){
			alpha = 1.0;
		}else if((xx>0.5-v_masksize)&&(xx<0.5+v_masksize)){
			float  index =  xx - (0.5 - v_masksize);
			float al =  index * 1.0 / (v_masksize * 2.0);
			alpha =al;
 		}else{
 			float  index =  xx - (-0.5 - v_masksize);
			float al =  index * 1.0 / (v_masksize * 2.0);
			alpha = 1.0-al;
 		}
 		gl_FragColor = colA * alpha + colB * (1.0 - alpha);	
	}else if(modetype==3){//前后模式
		gl_FragColor  = vec4(255.0,0.0,0.0,1.0);
	}
}