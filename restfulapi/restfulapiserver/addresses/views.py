from django.contrib.auth import authenticate
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import Addresses
from .serializers import AddressesSerializer
from rest_framework.parsers import JSONParser

# Create your views here.

@csrf_exempt
def address_list(request):
    if request.method == 'GET':
        query_set = Addresses.objects.all()
        serializer = AddressesSerializer(query_set, many=True)
        return JsonResponse(serializer.data, safe=False)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = AddressesSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)


@csrf_exempt
def address(request, pk):

    obj = Addresses.objects.get(pk=pk)

    if request.method == 'GET':
        serializer = AddressesSerializer(obj)
        return JsonResponse(serializer.data, safe=False)

    elif request.method == 'PUT':
        data = JSONParser().parse(request)
        serializer = AddressesSerializer(obj, data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)

    elif request.method == 'DELETE':
        obj.delete()
        return HttpResponse(status=204)


@csrf_exempt
def login(request):

    if request.method == 'POST':
        print("request " + str(request))
        print("body " + str(request.body))
        userid = request.POST.get("userid", "")
        userpw = request.POST.get("userpw", "")

        print("userid = " + userid + "  userpw = " + userpw)
        login_result = authenticate(username=userid, password=userpw)

        print("userid = " + userid + " result = " + str(login_result))

        if login_result:
            return HttpResponse(status=200)
        else:
            return render(request, "addresses/login.html", status=401)

        # data = JSONParser().parse(request)
        # search_name = data['name']
        # print(search_name)
        # obj = Addresses.objects.get(name=search_name)
        # print(obj.phone_number)
        # if data['phone_number'] == obj.phone_number:
        #     return HttpResponse(status=200)
        # else:
        #     return HttpResponse(status=400)

    return render(request, 'addresses/login.html')



@csrf_exempt
def app_login(request):

    if request.method == 'POST':
        print("???????????? ??????" + str(request.body))
        id = request.POST.get('userid', '')
        pw = request.POST.get('userpw', '')
        print("id = " + id + " pw = " + pw)

        result = authenticate(username=id, password=pw)

        if result:
            print("????????? ??????!")
            return JsonResponse({'code': '0000', 'msg': '????????????????????????.'}, status=200)
        else:
            print("??????")
            return JsonResponse({'code': '1001', 'msg': '????????????????????????.'}, status=200)