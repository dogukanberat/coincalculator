# append the following in your existing dockerfile
# this will create a multi-stage docker build for minimizing size and security vulnerabilities
# ...

FROM steebchen/nginx-spa:stable

# adapt the `dist/` folder to the output directory your build tool uses (such as `dist/`, `build/` or `www/`).
COPY dist/ /app

EXPOSE 80

CMD ["nginx"]