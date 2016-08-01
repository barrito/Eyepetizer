#extension GL_OES_EGL_image_external : require
precision mediump float;
varying vec2 v_TexCoordinate;
varying highp float v_pos_x;

uniform samplerExternalOES u_Texture;
uniform bool forward;
uniform bool front;
void main(){
	if(forward)
	{
		if(v_TexCoordinate.y < 0.999 && v_TexCoordinate.y >0.001){
     	   gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
   		 }else {
    	   gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
		}
 	}else if(front){
 		if(v_pos_x >0.65||v_pos_x <-0.65){
			gl_FragColor  = vec4(0.0,0.0,0.0,1.0);
		}else {
				gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
		}
 	}else{	
		 gl_FragColor = texture2D(u_Texture, v_TexCoordinate);
	}

}