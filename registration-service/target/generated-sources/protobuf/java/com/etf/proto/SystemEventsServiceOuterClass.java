// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SystemEventsService.proto

package com.etf.proto;

public final class SystemEventsServiceOuterClass {
  private SystemEventsServiceOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_etf_SystemEventRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_etf_SystemEventRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_etf_SystemEventResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_etf_SystemEventResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031SystemEventsService.proto\022\007com.etf\"\250\001\n" +
      "\022SystemEventRequest\022\021\n\ttimestamp\030\001 \001(\t\022\031" +
      "\n\021microservice_name\030\002 \001(\t\022\014\n\004user\030\003 \001(\t\022" +
      "\023\n\013action_type\030\004 \001(\t\022\025\n\rresource_name\030\005 " +
      "\001(\t\022\025\n\rresponse_type\030\006 \001(\t\022\023\n\013descriptio" +
      "n\030\007 \001(\t\"\'\n\023SystemEventResponse\022\020\n\010respon" +
      "se\030\001 \001(\t2b\n\023SystemEventsService\022K\n\016syste" +
      "mEventLog\022\033.com.etf.SystemEventRequest\032\034" +
      ".com.etf.SystemEventResponseB\021\n\rcom.etf." +
      "protoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_etf_SystemEventRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_etf_SystemEventRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_etf_SystemEventRequest_descriptor,
        new java.lang.String[] { "Timestamp", "MicroserviceName", "User", "ActionType", "ResourceName", "ResponseType", "Description", });
    internal_static_com_etf_SystemEventResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_etf_SystemEventResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_etf_SystemEventResponse_descriptor,
        new java.lang.String[] { "Response", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}