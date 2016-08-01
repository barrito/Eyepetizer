#extension GL_OES_EGL_image_external : require
precision highp float;
precision highp int;
precision lowp samplerExternalOES;

varying highp vec2 v_TexCoordinateA;
varying highp vec2 v_TexCoordinateB;
varying highp float v_pos_x;

uniform samplerExternalOES u_Texture;
uniform bool isright; 
uniform int modetype;



void main() {
    	highp vec4 colA = texture2D(u_Texture, v_TexCoordinateA);
    	highp vec4 colB = texture2D(u_Texture, v_TexCoordinateB);
		if(isright){
    		if(modetype == 2){//前后平面模式
				if(v_pos_x >0.55||v_pos_x <-0.55){
					gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
				}else {
					gl_FragColor = colB;
				}
    		}else{
				if(v_pos_x >0.20){
					gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
				}else {
   			  		gl_FragColor = colB;
				}
			}
		}else{
    		if(modetype == 1){//一般模式
				if(v_pos_x >0.20){
					gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
				}else {
					gl_FragColor = colA;
				}
			}else if(modetype == 2){//平面模式
				if(v_pos_x >0.55||v_pos_x <-0.55){
					gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
				}else {
					gl_FragColor = colA;
				}
			}else if(modetype == 3){//原型
				gl_FragColor = colA;
			}				
        }
    }